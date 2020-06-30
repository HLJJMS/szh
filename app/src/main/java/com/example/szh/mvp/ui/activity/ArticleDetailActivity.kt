package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerArticleDetailComponent
import com.example.szh.di.module.ArticleDetailModule
import com.example.szh.mvp.contract.ArticleDetailContract
import com.example.szh.mvp.presenter.ArticleDetailPresenter

import com.example.szh.R
import com.example.szh.bean.ArticleDetailBean
import com.example.szh.tools.MyGlide
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/27/2020 14:28
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
class ArticleDetailActivity : BaseActivity<ArticleDetailPresenter>(), ArticleDetailContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerArticleDetailComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .articleDetailModule(ArticleDetailModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_article_detail //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.getData(intent.getStringExtra("id"),"")

    }

    override fun getDataSuccess(bean: ArticleDetailBean.ResultBean) {
        titleBar.setCenterText(bean.articles.dirname)
        tv_look.setText(bean.articles.view.toString() + "阅读")
        tv_detail.text = Html.fromHtml(bean.articles.contenttext.toString(),FROM_HTML_MODE_COMPACT)
        if(null!=bean.articles.pic){
            MyGlide.loadImage(this,bean.articles.pic.toString(),iv_img)
        }
        tv_title.setText(bean.articles.title)
        tv_off.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.cllection(intent.getStringExtra("id"),Math.abs(bean.collection-1).toString())
        }
        tv_like.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            mPresenter?.like(intent.getStringExtra("id"),Math.abs(bean.articles.like-1).toString())
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
