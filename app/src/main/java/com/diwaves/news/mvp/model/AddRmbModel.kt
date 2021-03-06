package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.PayBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.AddRmbContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import com.diwaves.news.network.service.UserService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/16/2020 14:50
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class AddRmbModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    AddRmbContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun postData(id: String, number: String): Observable<PayBean> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .wxPay(id, number)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
