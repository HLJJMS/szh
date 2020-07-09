package com.example.szh.mvp.model

import android.app.Application
import com.example.szh.bean.RmbMaketBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.example.szh.mvp.contract.RmbMaketMainContract
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.service.CommentService
import com.example.szh.network.service.HomeService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/09/2020 11:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class RmbMaketMainModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    RmbMaketMainContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(
        id: String,
        dirid: String,
        type: String
    ): Observable<RmbMaketBean> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).getPredict(id, dirid, type)
    }

    override fun postData(
        id: String,
        silver: String,
        predictid: String,
        option: String,
        hide: String
    ): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(HomeService::class.java).addPredict(id, silver, predictid, option, hide)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
