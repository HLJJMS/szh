package com.example.szh.mvp.model

import android.app.Application
import com.example.szh.bean.ArticleDetailBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.example.szh.mvp.contract.ArticleDetailContract
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.service.HomeService
import io.reactivex.Observable


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

    override fun onDestroy() {
        super.onDestroy();
    }
}
