package com.diwaves.news.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.ResealesPhotoActivityContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import com.diwaves.news.network.service.UserService
import io.reactivex.Observable
import okhttp3.RequestBody


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/16/2020 20:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class ResealesPhotoActivityModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ResealesPhotoActivityContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }

    override fun postPhoto(body: RequestBody): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).postPhoto(body)
    }

    override fun postArticle(
        id: String,
        audiopath: String,
        contenttext: String,
        state: String,
        title: String
    ): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .upImageArticle(id, audiopath, contenttext, state, title)
    }
}
