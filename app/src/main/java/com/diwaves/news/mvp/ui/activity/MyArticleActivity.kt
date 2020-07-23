package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwaves.news.R
import com.diwaves.news.adapter.MyArticleAdapter
import com.diwaves.news.bean.MyArticleBean
import com.diwaves.news.di.component.DaggerMyArticleComponent
import com.diwaves.news.di.module.MyArticleModule
import com.diwaves.news.mvp.contract.MyArticleContract
import com.diwaves.news.mvp.presenter.MyArticlePresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_my_article.*
import kotlinx.android.synthetic.main.activity_my_article.recycler


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/20/2020 18:02
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
class MyArticleActivity : BaseActivity<MyArticlePresenter>(), MyArticleContract.View {
    var adapter: MyArticleAdapter? = MyArticleAdapter()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerMyArticleComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .myArticleModule(MyArticleModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_my_article //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        if (intent.getStringExtra("type").equals("0")) {
            title_bar.setCenterText("我的草稿")
        } else {
            title_bar.setCenterText("我的文章")
        }
        title_bar.setBackClick {
            finish()
        }
        mPresenter?.getData(intent.getStringExtra("type"))

    }

    override fun getSuccess(bean: MutableList<MyArticleBean.ResultBean.ArticlesListBean>) {
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
