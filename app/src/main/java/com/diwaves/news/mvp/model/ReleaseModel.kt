package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.BangdanBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.ReleaseContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import io.reactivex.Observable
import okhttp3.RequestBody


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/11/2020 23:04
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class ReleaseModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    ReleaseContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun postPhoto(body: RequestBody): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).postPhoto(body)
    }

    override fun postData(
        id: String,
        title: String,

        contenttext: String,
        dirid: String,
        dirname: String,
        state: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).addArticle(id, title, contenttext, dirid, dirname, state)
    }


    override fun onDestroy() {
        super.onDestroy();
    }
    override fun getData(): Observable<BangdanBean> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getBangdan()
    }
}
