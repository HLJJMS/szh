package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.YuCeCommentBean
import com.diwaves.news.bean.YuCeCommentUpBean
import com.diwaves.news.bean.YuCeDetail
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.YuCeCommentContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.CommentService
import com.diwaves.news.network.service.HomeService
import com.diwaves.news.network.service.WalletService
import com.diwaves.news.tools.SPToll
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/23/2020 22:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class YuCeCommentModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    YuCeCommentContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getDownData(
        type: String,
        zstype: String
    ): Observable<BaseBean.BaseResponse<MutableList<YuCeCommentBean>>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java)
            .getYuCeCommentDown(SPToll(mApplication).getId(), zstype, type)
    }

    override fun getUpData(zstype: String): Observable<BaseBean.BaseResponse<YuCeDetail>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java)
            .getYuCeCommentUp(SPToll(mApplication).getId(), zstype)
    }

    override fun goodComment(
        id: String,
        commentid: String,
        type: String
    ): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).goodComment(id,  commentid, type)

    }

    override fun pingbi(
        id: String,
        title: String,
        articleid: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).pingbi(id, title,articleid)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
