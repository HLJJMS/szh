package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.MessageBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.MessageContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import com.diwaves.news.network.service.UserService
import com.diwaves.news.tools.SPToll
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 17:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class MessageModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    MessageContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(id: String): Observable<MessageBean> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java)
            .getMessage(id)
    }
    override fun pingbi(
        id: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).pingbiUser(SPToll(mApplication).getId(),id,"1")
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
