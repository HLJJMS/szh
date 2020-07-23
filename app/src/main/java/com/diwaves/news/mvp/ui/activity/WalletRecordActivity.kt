package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerWalletRecordComponent
import com.diwaves.news.di.module.WalletRecordModule
import com.diwaves.news.mvp.contract.WalletRecordContract
import com.diwaves.news.mvp.presenter.WalletRecordPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.WalletRecordAdapter
import com.diwaves.news.bean.WelltaRecordBean
import kotlinx.android.synthetic.main.activity_wallet_record.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2020 11:36
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
class WalletRecordActivity : BaseActivity<WalletRecordPresenter>(), WalletRecordContract.View {
     var adapter: WalletRecordAdapter = WalletRecordAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerWalletRecordComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .walletRecordModule(WalletRecordModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_wallet_record //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        mPresenter?.getList(intent.getStringExtra("type"))
        titlebar.setBackClick { finish() }
    }

    override fun success(bean: MutableList<WelltaRecordBean.ResultBean>) {
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
