package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.MyKLineBean
import com.diwaves.news.bean.KListBean

import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.KLineContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class KLineModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    KLineContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }

    override fun getData(
        id: String,
        type: String
    ): Observable<BaseBean.BaseResponse<KListBean>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getPredict(id, "1")
    }

    override fun getK(type: String): Observable<BaseBean.BaseResponse<MyKLineBean>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getK(type)
    }
}
