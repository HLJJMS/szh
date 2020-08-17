package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerTypeListActivityComponent
import com.diwaves.news.di.module.TypeListActivityModule
import com.diwaves.news.mvp.contract.TypeListActivityContract
import com.diwaves.news.mvp.presenter.TypeListActivityPresenter

import com.diwaves.news.R
import com.diwaves.news.adapter.TypeListAdapter
import com.diwaves.news.bean.TypeListBean
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_type_list.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/22/2020 14:38
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
class TypeListActivityActivity : BaseActivity<TypeListActivityPresenter>(),
    TypeListActivityContract.View {
    var adapter: TypeListAdapter = TypeListAdapter()
    var type = "0"
    var page = 1
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerTypeListActivityComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .typeListActivityModule(TypeListActivityModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_type_list //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        titleBar.setBackClick {
            finish()
        }
        titleBar.setCenterText(intent.getStringExtra("title"))
        recycler.layoutManager = LinearLayoutManager(this)
        mPresenter?.getData(intent.getStringExtra("id"), type, page)
        recycler.adapter = adapter
        tv_hot.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_111111))
            type = "0"
            page = 1
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
        tv_new.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_111111))
            type = "1"
            page = 1
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
        adapter.loadMoreModule.setOnLoadMoreListener {
            page++
            mPresenter?.getData(intent.getStringExtra("id"), type, page)
        }
        adapter.loadMoreModule.isAutoLoadMore = true
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        adapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
        adapter.setOnItemClickListener { adapter, view, position ->
            var intent: Intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("id", this.adapter.data.get(position).id.toString())
            if (null == this.adapter.data.get(position).pushid || "null".equals(
                    this.adapter.data.get(
                        position
                    ).pushid
                )
            ) {
                intent.putExtra("pushid", "")
            } else {
                intent.putExtra("pushid", this.adapter.data.get(position).pushid.toString())
            }
            startActivity(intent)
        }
    }

    override fun sunccess(bean: MutableList<TypeListBean.ResultBean.RecordsBean>) {

        if (page == 1) {
            adapter.setList(bean)
        } else {
            adapter.addData(bean)
        }
        if (bean.size == 0) {
            adapter.loadMoreModule.loadMoreEnd(true)
        } else {
            adapter.loadMoreModule.loadMoreComplete()
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
        finish()
    }
}
