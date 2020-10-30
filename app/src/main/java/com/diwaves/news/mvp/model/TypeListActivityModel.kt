package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.TypeListBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.TypeListActivityContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/22/2020 14:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class TypeListActivityModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    TypeListActivityContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(
        id: String,
        dirid: String,
        type: String,
        current: String,
        size: String
    ): Observable<TypeListBean> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getZXList(id, dirid,type,current,size)
    }
    override fun pingbi(
        id: String,
        title: String,
        articleid: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).pingbi(id, title,articleid)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
