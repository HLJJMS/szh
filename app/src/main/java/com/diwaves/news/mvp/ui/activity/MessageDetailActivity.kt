package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerMessageDetailComponent
import com.diwaves.news.di.module.MessageDetailModule
import com.diwaves.news.mvp.contract.MessageDetailContract
import com.diwaves.news.mvp.presenter.MessageDetailPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.MessageDetailAdapter
import com.diwaves.news.bean.ChartBean
import kotlinx.android.synthetic.main.activity_message_detail.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:24
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
class MessageDetailActivity : BaseActivity<MessageDetailPresenter>(), MessageDetailContract.View {
    var adapter: MessageDetailAdapter = MessageDetailAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMessageDetailComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .messageDetailModule(MessageDetailModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_message_detail //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick { finish() }
        recycler?.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        mPresenter?.getData(intent.getStringExtra("id"))
        rb_ok.setOnClickListener {
            mPresenter?.sendMessage(intent.getStringExtra("id"),et_message.text.toString())
            et_message.setText("")
        }
        titleBar.setCenterText(intent.getStringExtra("title"))
        titleBar.setEndTextClick {
            mPresenter?.getData(intent.getStringExtra("id"))
        }
    }

    override fun getDataSuccess(list: MutableList<ChartBean>) {
        adapter.setList(list)
    }


    override fun showLoading() {
        swipeLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeLayout.isRefreshing = false
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
