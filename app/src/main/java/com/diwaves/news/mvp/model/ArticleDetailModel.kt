package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.ArticleDetailBean
import com.diwaves.news.bean.CommentBean
import com.diwaves.news.bean.ShareBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.ArticleDetailContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.CommentService
import com.diwaves.news.network.service.HomeService
import io.reactivex.Observable
import okhttp3.MultipartBody


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
@ActivityScope
class ArticleDetailModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ArticleDetailContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(
        id: String,
        articleid: String,
        pushid: String
    ): Observable<ArticleDetailBean> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java)
            .getArticleDetail(id, articleid, pushid)
    }

    override fun like(
        id: String,
        articleid: String,
        like: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java)
            .likeArticle(id, articleid, like)
    }

    override fun cllection(
        id: String,
        articleid: String,
        cllection: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java)
            .cllectionArticle(id, articleid, cllection)
    }

    override fun addComment(body: MultipartBody): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(CommentService::class.java).addComment(body)
    }

    override fun getComment(
        id: String,
        articleid: String,
        current: String,
        type: String
    ): Observable<CommentBean> {
        return mRepositoryManager.obtainRetrofitService(CommentService::class.java)
            .getComment(id, articleid, current, type)
    }

    override fun goodComment(
        id: String,
        articleid: String,
        commentid: String,
        type: String
    ): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(CommentService::class.java).goodComment(id, articleid, commentid, type)

    }

    override fun commentReplay(
        id: String,
        articleid: String,
        commentid: String,
        neirong: String
    ): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(CommentService::class.java).commentReplay(id, articleid, commentid, neirong)
    }

    override fun pingbi(
        id: String,
        title: String,
        articleid: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).pingbi(id, title,articleid)
    }

    override fun sorce(
        id: String,
        sorce: String,
        articleid: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).sorce(id, sorce,articleid)
    }

    override fun getShare(id: String): Observable<BaseBean.BaseResponse<ShareBean>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getShare(id)
    }


    override fun onDestroy() {
        super.onDestroy();
    }
}
