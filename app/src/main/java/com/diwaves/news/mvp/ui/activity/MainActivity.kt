package com.diwaves.news.mvp.ui.activity

import android.content.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Gravity

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.diwaves.news.R
import com.diwaves.news.adapter.HomePageAdapter
import com.diwaves.news.bean.TabEntity
import com.diwaves.news.di.component.DaggerMainComponent
import com.diwaves.news.di.module.MainModule
import com.diwaves.news.eventbus.MainEvent
import com.diwaves.news.mvp.contract.MainContract
import com.diwaves.news.mvp.presenter.MainPresenter
import com.diwaves.news.mvp.ui.fragment.HomeFragment
import com.diwaves.news.mvp.ui.fragment.MessageFragment
import com.diwaves.news.mvp.ui.fragment.MyFragment
import com.diwaves.news.mvp.ui.fragment.WalletFragment
import com.diwaves.news.network.Api.APP_ID
import com.diwaves.news.tools.SPToll
import com.flyco.tablayout.listener.CustomTabEntity
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/02/2020 21:37
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {
    private val mTabEntities =
        ArrayList<CustomTabEntity>()
    val mFragments = ArrayList<Fragment>()

    var myFragment = MyFragment()
    val homeFragment = HomeFragment()
    val walletFragment = WalletFragment()
    val messageFragment = MessageFragment()

    var homePageAdapter: HomePageAdapter? = null
    var buttonList = ArrayList<ImageView>()
    var textList = ArrayList<TextView>()
    var isExit = false
    var context: Context? = null
    private var api: IWXAPI? = null

    var popupWindow: PopupWindow = PopupWindow();
    var view: View? = null
    var ivImg: ImageView? = null
    var ivTxt: ImageView? = null
    var ivClose: ImageView? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
        regToWx()
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    private fun regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true)

        // 将应用的appId注册到微信
        api!!.registerApp(APP_ID)

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {

                // 将该app注册到微信
                api?.registerApp(APP_ID)
            }
        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
    }

    override fun initData(savedInstanceState: Bundle?) {
        context = this
        mTabEntities.add(TabEntity("首页", R.mipmap.ic_home_home_on, R.mipmap.ic_home_home_off))
        mTabEntities.add(TabEntity("消息", R.mipmap.ic_home_talk_on, R.mipmap.ic_home_talk_off))
        mTabEntities.add(TabEntity("钱包", R.mipmap.ic_home_nike_on, R.mipmap.ic_home_nike_off))
        mTabEntities.add(TabEntity("我的", R.mipmap.ic_home_my_on, R.mipmap.ic_home_my_off))
        textList.add(tv_home)
        textList.add(tv_message)
        textList.add(tv_wallet)
        textList.add(tv_my)
        buttonList.add(iv_home)
        buttonList.add(iv_message)
        buttonList.add(iv_wallet)
        buttonList.add(iv_my)
        mFragments.add(homeFragment)
        mFragments.add(messageFragment)
        mFragments.add(walletFragment)
        mFragments.add(myFragment)
        homePageAdapter = HomePageAdapter(supportFragmentManager, mFragments)
        viewpager.adapter = homePageAdapter
        viewpager.setOffscreenPageLimit(4);
        iv_home.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                setButton(0)
            }
        iv_message.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setButton(1)
        }
        iv_wallet.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setButton(2)
        }
        iv_my.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setButton(3)
        }
        iv_add.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            if (SPToll(this).getId().equals("")) {
                startActivity(Intent(this, LoginActivity::class.java))

            } else {
                showPopWindow()
            }
        }
        tv_home.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                setButton(0)
            }
        tv_message.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setButton(1)
        }
        tv_wallet.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setButton(2)
        }
        tv_my.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setButton(3)
        }
        tv_add.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            if (SPToll(this).getId().equals("")) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                showPopWindow()
            }
        }
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                setButton(position)
            }

        })
        mPresenter?.getEveryAg()
        setPopWindow()
    }

    fun setButton(i: Int) {
        for (index in 0..3) {
            if (i == index) {
                buttonList.get(index).setImageResource(mTabEntities.get(index).tabSelectedIcon)
                textList.get(index).setTextColor(ContextCompat.getColor(this, R.color.color_2BA4D9))
                textList.get(index).text = mTabEntities.get(index).tabTitle
            } else {
                buttonList.get(index).setImageResource(mTabEntities.get(index).tabUnselectedIcon)
                textList.get(index).setTextColor(ContextCompat.getColor(this, R.color.black))
                textList.get(index).text = mTabEntities.get(index).tabTitle
            }

        }
        viewpager.currentItem = i

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onMessageEvent(event: MainEvent?) {/* Do something */
        if (event!!.isLogin) {
            walletFragment.getData()
            myFragment.getData()
            homeFragment.shuaixnData()
        } else {
            myFragment.clearData()
            walletFragment.getData()
        }

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event)
    }

    fun exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一退出程序", Toast.LENGTH_SHORT).show();
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            isExit = false
        }
    }


    fun setPopWindow() {
        view = LayoutInflater.from(this).inflate(R.layout.pop_release, null);
        popupWindow?.contentView = view
        popupWindow?.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow?.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow?.setOutsideTouchable(true)
        popupWindow?.setFocusable(true) //点击返回键取消
        popupWindow?.setBackgroundDrawable(BitmapDrawable())
        ivImg = view?.findViewById(R.id.iv_photo);
        ivTxt = view?.findViewById(R.id.iv_txt);
        ivClose = view?.findViewById(R.id.iv_close);
        ivClose?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)
            ?.subscribe {
                popupWindow?.dismiss()
            }
        ivImg!!.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                startActivity(Intent(this,ResealesPhotoActivityActivity::class.java))
            }
        ivTxt!!.clicks().throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                startActivity(Intent(context, ReleaseActivity::class.java))
            }
    }

    fun showPopWindow() {
        popupWindow?.showAtLocation(getWindow().decorView, Gravity.NO_GRAVITY, 0, 0)

    }

}


