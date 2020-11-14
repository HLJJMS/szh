package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerEexamineComponent
import com.diwaves.news.di.module.EexamineModule
import com.diwaves.news.mvp.contract.EexamineContract
import com.diwaves.news.mvp.presenter.EexaminePresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.EexamineAdapter
import com.diwaves.news.bean.MessageAuditBean
import kotlinx.android.synthetic.main.activity_eexamine.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/12/2020 17:07
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
class EexamineActivity : BaseActivity<EexaminePresenter>(), EexamineContract.View {
    var adapter: EexamineAdapter = EexamineAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEexamineComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .eexamineModule(EexamineModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_eexamine //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        mPresenter?.getData()
        adapter.addChildClickViewIds(R.id.tv_ok,R.id.tv_ok)
        adapter.setOnItemChildClickListener { adapters, view, position ->
            if(view.id==R.id.tv_ok){
                mPresenter?.setData(adapter.data.get(position).articlesid.toString(),"1")
            }else if(view.id==R.id.tv_no){
                mPresenter?.setData(adapter.data.get(position).articlesid.toString(),"2")
            }
        }
    }

    override fun seccuse(list: MutableList<MessageAuditBean.ResultEntity>) {
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
