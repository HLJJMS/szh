package com.diwaves.news.mvp.ui.fragment

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwaves.news.R
import com.diwaves.news.adapter.MyFramgentAdapter
import com.diwaves.news.bean.MyInfoBean
import com.diwaves.news.bean.MyItemBean
import com.diwaves.news.di.component.DaggerMyComponent
import com.diwaves.news.di.module.MyModule
import com.diwaves.news.mvp.contract.MyContract
import com.diwaves.news.mvp.presenter.MyPresenter
import com.diwaves.news.mvp.ui.activity.*
import com.diwaves.news.tools.MyGlide
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yzq.zxinglibrary.android.CaptureActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_my.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 15:39
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
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */


class MyFragment : BaseFragment<MyPresenter>(), MyContract.View {
    private var myFramgentAdapter: MyFramgentAdapter? = null
    private var list: ArrayList<MyItemBean> = ArrayList()
    var REQUEST_CODE_SCAN = 1001;

    companion object {
        fun newInstance(): MyFragment {
            val fragment = MyFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMyComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myModule(MyModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        getData()
        iv_setting.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, SettingActivity::class.java))
        }

        tv_add_friend.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, AddFriendActivity::class.java))
        }
        rb_login.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(context, LoginActivity::class.java))
        }

        iv_scan.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            val rxPermissions: RxPermissions = RxPermissions(this)
            rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(Consumer<Boolean>() {
                    if (it) {
                        //扫描二维码
                        val intent = Intent(context, CaptureActivity::class.java)
                        startActivityForResult(intent, REQUEST_CODE_SCAN)
                    } else {
                        MyToast().makeToast(context?.applicationContext!!, "暂无权限")
                    }
                });


        }

    }

    fun getData() {
        if (!SPToll(mContext).getId().equals("")) {
            mPresenter?.getData()
            tv_id.visibility = View.VISIBLE

            tv_position.visibility = View.VISIBLE
            tv_add_friend.visibility = View.VISIBLE
            iv_add_friend.visibility = View.VISIBLE
            iv_admin.visibility = View.VISIBLE
            iv_vip.visibility = View.VISIBLE

            rb_login.visibility = View.GONE
        } else {
            clearData()

        }
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     *fun setData(data:Any) {
     *   if(data is Message){
     *       when (data.what) {
     *           0-> {
     *               //根据what 做你想做的事情
     *           }
     *           else -> {
     *           }
     *       }
     *   }
     *}
     *
     *
     *
     *
     *
     * // call setData(Object):
     * val data = Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    override fun setData(data: Any?) {

    }

    override fun success(bean: MyInfoBean) {
        recycler.visibility = View.VISIBLE
        MyGlide.loadImageCircle(mContext, bean.user.avatarUrl, iv_head)
        tv_name.text = bean.user.name

        tv_id.text = "数字号 : " + bean.user.wxname

        tv_day.text = bean.viewdaycount
        tv_week.text = bean.viewweekcount
        tv_month.text = bean.viewmonthcount
        tv_create_level.text = bean.user.createlevel
        tv_test_level.text = bean.user.predictlevel
        list?.clear()
        list?.add(
            MyItemBean(
                "关注", R.mipmap.ic_my_tieba, bean.user.focus, Intent(
                    context,
                    MyFansAndLookActivity::class.java
                ).putExtra("fans", false)
            )
        )
        list?.add(
            MyItemBean(
                "朋友", R.mipmap.ic_my_message, bean.user.friends, Intent(
                    context,
                    MyFriendActivity::class.java
                )
            )
        )
        list?.add(
            MyItemBean(
                "预测", R.mipmap.ic_my_clock, bean.yuce, Intent(
                    context,
                    MyYuCeActivity::class.java
                )
            )
        )
        list?.add(
            MyItemBean(
                "屏蔽", R.mipmap.ic_pingbi, bean.pingbi, Intent(
                    context,
                    PingBiActivityActivity::class.java
                )
            )
        )
        list?.add(
            MyItemBean(
                "处罚", R.mipmap.ic_chufa, bean.chufa, Intent(
                    context,
                    MyCollectActivity::class.java
                )
            )
        )
        list?.add(
            MyItemBean(
                "收藏", R.mipmap.ic_start, bean.shoucang, Intent(
                    context,
                    MyCollectActivity::class.java
                )
            )
        )
        list?.add(
            MyItemBean(
                "草稿箱", R.mipmap.ic_test, bean.caogaoxiang, Intent(
                    context,
                    MyArticleActivity::class.java
                ).putExtra("type", "0")
            )
        )
        myFramgentAdapter = MyFramgentAdapter(list)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = myFramgentAdapter
        myFramgentAdapter?.setList(list)
        myFramgentAdapter?.setOnItemClickListener { adapter, view, position ->
            startActivity(list.get(position).intent)
        }
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

    }

    fun clearData() {
        tv_id.visibility = View.INVISIBLE

        tv_position.visibility = View.INVISIBLE
        tv_add_friend.visibility = View.INVISIBLE
        iv_add_friend.visibility = View.INVISIBLE
        iv_admin.visibility = View.INVISIBLE
        iv_vip.visibility = View.INVISIBLE

        rb_login.visibility = View.VISIBLE
        recycler.visibility = View.GONE
        MyGlide.loadImageCircle(mContext, "", iv_head)
        tv_name.text = ""

        tv_id.text = "数字号 : "

        tv_day.text = ""
        tv_week.text = ""
        tv_month.text = ""
        tv_create_level.text = ""
        tv_test_level.text = ""
    }
}
