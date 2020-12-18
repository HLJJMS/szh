package com.diwaves.news.mvp.model

import android.app.Application
import com.diwaves.news.bean.FocusListBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.FragmentScope
import javax.inject.Inject

import com.diwaves.news.mvp.contract.LookContract
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.service.HomeService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 16:16
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class LookModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    LookContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(id: String): Observable<FocusListBean> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getMyList(id,"1","100")
    }

    override fun onDestroy() {
        super.onDestroy();
    }
    override fun pingbi(
        id: String,
        title: String,
        articleid: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).pingbi(id, title,articleid)
    }
}
