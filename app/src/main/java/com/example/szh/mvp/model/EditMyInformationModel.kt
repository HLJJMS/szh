package com.example.szh.mvp.model

import android.app.Application
import com.example.szh.bean.MyInfoBean
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.example.szh.mvp.contract.EditMyInformationContract
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.service.UserService
import com.example.szh.network.service.WalletService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 09:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class EditMyInformationModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    EditMyInformationContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;
    override fun getData(id: String): Observable<BaseBean.BaseResponse<MyInfoBean>> {
        return mRepositoryManager.obtainRetrofitService(UserService::class.java).getUserInfo(id)
    }

    override fun onDestroy() {
        super.onDestroy();
    }
}
