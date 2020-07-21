package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerMyYuCeComponent
import com.example.szh.di.module.MyYuCeModule
import com.example.szh.mvp.contract.MyYuCeContract
import com.example.szh.mvp.presenter.MyYuCePresenter

import com.example.szh.R
import com.example.szh.adapter.MyYuCeAdapter
import com.example.szh.bean.YuCeBean
import kotlinx.android.synthetic.main.activity_my_yu_ce.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/21/2020 15:13
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
class MyYuCeActivity : BaseActivity<MyYuCePresenter>(), MyYuCeContract.View {
    var adapter: MyYuCeAdapter? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMyYuCeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myYuCeModule(MyYuCeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_yu_ce //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick {
            finish()
        }
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        mPresenter?.getData()
    }

    override fun success(bean: MutableList<YuCeBean.ResultBean.ListBean.RecordsBean>) {
        adapter?.setList(bean)
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
