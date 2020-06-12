package com.example.szh.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.example.szh.mvp.contract.LoginContract
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.bean.LoginBean
import com.example.szh.network.service.LoginService
import io.reactivex.Observable


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/05/2020 15:45
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class LoginModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager),
    LoginContract.Model {
    @Inject
    lateinit var mGson: Gson;

    @Inject
    lateinit var mApplication: Application;

    override fun postData(
        loginType: String,
        password: String,
        phone: String,
        verificaCode: String
    ): Observable<BaseBean.BaseResponse<LoginBean.Login>> {
        return mRepositoryManager.obtainRetrofitService(LoginService::class.java)
            .login(loginType, password, phone, verificaCode)
    }

    override fun getCode(phone: String): Observable<BaseBean.BaseResponse<String>> {
        return mRepositoryManager.obtainRetrofitService(LoginService::class.java).getCode(phone, 1)
    }

    override fun onDestroy() {
        super.onDestroy();
    }

}
