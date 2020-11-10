package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerNewFriendComponent
import com.diwaves.news.di.module.NewFriendModule
import com.diwaves.news.mvp.contract.NewFriendContract
import com.diwaves.news.mvp.presenter.NewFriendPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.NewFriendAdapter
import com.diwaves.news.bean.NewFriendBean
import kotlinx.android.synthetic.main.activity_new_friend.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/30/2020 17:10
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
class NewFriendActivity : BaseActivity<NewFriendPresenter>(), NewFriendContract.View {
    var adapter: NewFriendAdapter = NewFriendAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerNewFriendComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .newFriendModule(NewFriendModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_new_friend //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        mPresenter?.getNewFriendDat()
        adapter.addChildClickViewIds(R.id.ok, R.id.no)
        adapter.setOnItemChildClickListener { adapter, view, position ->
            if (view.id == R.id.ok) {
                mPresenter?.okAndNo("2")
            } else if (view.id == R.id.no) {
                mPresenter?.okAndNo("1")
            }
        }
    }

    override fun getDataSuccess(list: MutableList<NewFriendBean.ResultEntity>) {
        adapter.setList(list)
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
