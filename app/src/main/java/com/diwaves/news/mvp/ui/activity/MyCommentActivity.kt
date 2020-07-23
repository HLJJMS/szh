package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerMyCommentComponent
import com.diwaves.news.di.module.MyCommentModule
import com.diwaves.news.mvp.contract.MyCommentContract
import com.diwaves.news.mvp.presenter.MyCommentPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.MyCommentAdapter
import com.diwaves.news.bean.MyCommentBean
import kotlinx.android.synthetic.main.activity_my_comment.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 19:31
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
class MyCommentActivity : BaseActivity<MyCommentPresenter>(), MyCommentContract.View {
    var adapter: MyCommentAdapter = MyCommentAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMyCommentComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myCommentModule(MyCommentModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_comment //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        mPresenter?.getData()
    }

    override fun success(bean: MutableList<MyCommentBean.ResultBean.ListBean.RecordsBean>) {
        adapter.setList(bean)
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
