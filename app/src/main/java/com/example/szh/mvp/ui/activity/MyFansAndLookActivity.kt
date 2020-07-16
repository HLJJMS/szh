package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerMyFansAndLookComponent
import com.example.szh.di.module.MyFansAndLookModule
import com.example.szh.mvp.contract.MyFansAndLookContract
import com.example.szh.mvp.presenter.MyFansAndLookPresenter

import com.example.szh.R
import com.example.szh.adapter.FansAndLookAdapter
import com.example.szh.bean.FriendListBean
import kotlinx.android.synthetic.main.activity_my_fans_and_look.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 20:04
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
class MyFansAndLookActivity : BaseActivity<MyFansAndLookPresenter>(), MyFansAndLookContract.View {
    var adapter: FansAndLookAdapter? = null
    var type: String = "0"
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMyFansAndLookComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myFansAndLookModule(MyFansAndLookModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_fans_and_look //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        if (intent.getBooleanExtra("fans", false)) {
            titlebar.setCenterText("我的粉丝")
            adapter = FansAndLookAdapter(0)
            type = "0"
        } else {
            titlebar.setCenterText("我的关注")
            adapter = FansAndLookAdapter(1)
            type = "1"
        }
        titlebar.setBackClick {
            finish()
        }
        adapter?.addChildClickViewIds(R.id.rb_ok, R.id.rb_no)
        adapter?.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.rb_ok) {

            } else if (view.id == R.id.rb_no) {

            }
        }
    }

    override fun success(bean: FriendListBean) {
        adapter?.setList(bean.result)
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
}
