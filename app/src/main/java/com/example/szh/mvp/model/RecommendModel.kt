package com.example.szh.mvp.model

import android.app.Application
import com.example.szh.bean.MyInfoBean
import com.example.szh.bean.RecommendBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.example.szh.mvp.contract.RecommendContract
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.service.HomeService
import com.example.szh.network.service.UserService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 14:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class RecommendModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    RecommendContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun onDestroy() {
        super.onDestroy();
    }
    override fun getData(id: String): Observable<RecommendBean> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getHomeRecommended(id)
    }
}
