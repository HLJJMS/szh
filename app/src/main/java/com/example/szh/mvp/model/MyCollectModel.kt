package com.example.szh.mvp.model

import android.app.Application
import com.example.szh.bean.CollectBean
import com.example.szh.bean.FocusListBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.example.szh.mvp.contract.MyCollectContract
import com.example.szh.network.service.HomeService
import com.example.szh.network.service.UserService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/15/2020 17:49
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class MyCollectModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    MyCollectContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;


    override fun getData(id: String, page: String, size: String): Observable<CollectBean> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .getCollectionList(id, page, size)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
