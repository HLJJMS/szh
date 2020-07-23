package com.diwaves.news.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.LoginContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.bean.LoginBean
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


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
class LoginPresenter
@Inject
constructor(model: LoginContract.Model, rootView: LoginContract.View) :
    BasePresenter<LoginContract.Model, LoginContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mImageLoader: ImageLoader

    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }

    fun getCode(phone: String) {
        mRootView.showLoading()
        mModel.getCode(phone).compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
            ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
            override fun onNext(t: BaseBean.BaseResponse<String>) {
                if (t.code.equals(Api.SUCCESS)) {
                    mRootView.getCodeSuccess()
                } else {
                    mRootView.getCodeFail()
                }
                MyToast().makeToast(mApplication, t.message)
                mRootView.hideLoading()
            }

        })
    }

    fun postData(
        loginType: String,
        password: String,
        phone: String,
        verificaCode: String
    ) {
        mRootView.showLoading()
        mModel.postData(loginType, password, phone, verificaCode)
            .compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
            ErrorHandleSubscriber<BaseBean.BaseResponse<LoginBean.Login>>(mErrorHandler) {
            override fun onNext(t: BaseBean.BaseResponse<LoginBean.Login>) {
                mRootView.hideLoading()
                if (t.code.equals(Api.SUCCESS)) {
                    t.result?.id?.let { SPToll(mApplication).setId(it) }
                    t.result?.phone?.let { SPToll(mApplication).setPhone(it) }
                    SPToll(mApplication).setPassWord(password)
                    mRootView.loginSuccess()

                } else {
                    MyToast().makeToast(mApplication, t.message)
                }

            }

        })
    }
}
