package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.szh.R
import com.example.szh.adapter.HomePageAdapter
import com.example.szh.bean.TabEntity
import com.example.szh.di.component.DaggerMainComponent
import com.example.szh.di.module.MainModule
import com.example.szh.eventbus.MainEvent
import com.example.szh.mvp.contract.MainContract
import com.example.szh.mvp.presenter.MainPresenter
import com.example.szh.mvp.ui.fragment.HomeFragment
import com.example.szh.mvp.ui.fragment.MessageFragment
import com.example.szh.mvp.ui.fragment.MyFragment
import com.example.szh.mvp.ui.fragment.WalletFragment
import com.flyco.tablayout.listener.CustomTabEntity
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
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
    private val mFragments = ArrayList<Fragment>()
    private var myFragment = MyFragment()
    private val homeFragment = HomeFragment()
    private val walletFragment = WalletFragment()
    private val messageFragment = MessageFragment()
    private var homePageAdapter: HomePageAdapter? = null
    private var buttonList = ArrayList<ImageView>()
    private var textList = ArrayList<TextView>()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
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
            startActivity(Intent(this, RegisterActivity::class.java))
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
    fun onMessageEvent(event: MainEvent?) { /* Do something */
        walletFragment.getData()
    }

    override fun onStart() {
        super.onStart()
    }
}


