package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerAddRmbComponent
import com.diwaves.news.di.module.AddRmbModule
import com.diwaves.news.mvp.contract.AddRmbContract
import com.diwaves.news.mvp.presenter.AddRmbPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.AddRmbAdapter
import com.diwaves.news.bean.PayBean
import com.diwaves.news.network.Api
import com.jakewharton.rxbinding3.view.clicks
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import kotlinx.android.synthetic.main.activity_add_rmb.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/16/2020 14:50
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
class AddRmbActivity : BaseActivity<AddRmbPresenter>(), AddRmbContract.View {
    var adapter: AddRmbAdapter = AddRmbAdapter()
    var listRmb: MutableList<String> = ArrayList<String>()
    var au_munber = "5"
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerAddRmbComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .addRmbModule(AddRmbModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_add_rmb //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick {
            finish()
        }
        listRmb.add("5")
        listRmb.add("10")
        listRmb.add("20")
        listRmb.add("30")
        listRmb.add("50")
        listRmb.add("100")
        adapter.setList(listRmb)
        adapter.setClickPosition(0)
        rv_botton.layoutManager =  GridLayoutManager(this, 3)
        rv_botton.adapter = adapter
        adapter.setOnItemClickListener { adapters, view, position ->
            adapter.setClickPosition(position)
            au_munber = adapter.data.get(position)
            tv_surplus.text = au_munber + "金币"
        }
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.payData(au_munber)
        }
    }

    override fun success(bean: PayBean.ResultBean) {
        var api: IWXAPI
        api = WXAPIFactory.createWXAPI(this, Api.APP_ID, true)
        var request: PayReq = PayReq()
        request.appId = Api.APP_ID;
        request.partnerId = bean.partnerid;
        request.prepayId = bean.prepayid;
        request.packageValue = "Sign=WXPay";
        request.nonceStr = bean.noncestr;
        request.timeStamp = bean.timestamp;
        request.sign = bean.sign;
        api.sendReq(request);
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
