package com.diwaves.news.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.RegisterContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.tools.MyToast
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/09/2020 11:40
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class RegisterPresenter
@Inject
constructor(model: RegisterContract.Model, rootView: RegisterContract.View) :
    BasePresenter<RegisterContract.Model, RegisterContract.View>(model, rootView) {
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
    //获取验证码
    fun getCode(phone: String) {
        mModel.getCode(phone).compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
            ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
            override fun onNext(t: BaseBean.BaseResponse<String>) {
                if (t.code.equals(Api.SUCCESS)) {
                    mRootView.getCodeSuccess()
                } else {
                    mRootView.getCodeFail()
                }
                MyToast().makeToast(mApplication, t.message)
            }

        })
    }
    //注册接口
    fun postData(name:String,password:String , phone :String , verificaCode :String){
        mModel.postData(name, password, phone, verificaCode).compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(object :ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler){
            override fun onNext(t: BaseBean.BaseResponse<String>) {
                if (t.code.equals(Api.SUCCESS)) {
                    mRootView.getCodeSuccess()
                } else {
                    mRootView.getCodeFail()
                }
                MyToast().makeToast(mApplication, t.message)
            }

        })
    }
}
