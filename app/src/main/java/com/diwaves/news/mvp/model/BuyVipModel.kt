package com.diwaves.news.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.BuyVipContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.UserService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/26/2020 16:01
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class BuyVipModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    BuyVipContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun postData(id: String, month: String): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .buyVip(id, month)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
