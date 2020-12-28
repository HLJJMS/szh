package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.ChartBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.MessageDetailContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import com.diwaves.news.network.service.UserService
import com.diwaves.news.tools.SPToll
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class MessageDetailModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    MessageDetailContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(id: String): Observable<BaseBean.BaseResponse<MutableList<ChartBean>>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .myChatList(id, SPToll(mApplication).getId())
    }


    override fun sendMessage(id: String, message: String): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .sendMessage(id, SPToll(mApplication).getId(),message)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
