package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.MessageAuditBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.EexamineContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.LoginService
import com.diwaves.news.network.service.UserService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/12/2020 17:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class EexamineModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    EexamineContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(id: String): Observable<MessageAuditBean> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java).getExamineList(id)
    }

    override fun setData(
        id: String,
        type: String,
        articlesid: String
    ): Observable<BaseBean.BaseResponse<Any>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .examineDo(id, type, articlesid)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
