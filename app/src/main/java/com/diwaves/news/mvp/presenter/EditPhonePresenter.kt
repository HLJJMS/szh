package com.diwaves.news.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.EditPhoneContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 11:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class EditPhonePresenter
@Inject
constructor(model: EditPhoneContract.Model, rootView: EditPhoneContract.View) :
    BasePresenter<EditPhoneContract.Model, EditPhoneContract.View>(model, rootView) {
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
    fun getCode(phone: String, type: String) {
        mModel.getCode(phone, type).compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
            ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
            override fun onNext(t: BaseBean.BaseResponse<String>) {
                if (t.code.equals(Api.SUCCESS)) {
                    mRootView.codeSuccess()
                } else {
                    mRootView.codeFail()
                }
                MyToast().makeToast(mApplication, t.message)
            }

        })
    }

    //更改手机号
    fun editPhone(phone: String, code: String) {
        mModel.editPhone(SPToll(mApplication).getId(), phone, code)
            .compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.editSuccess()
                    } else {
                        mRootView.editFail()
                    }
                    MyToast().makeToast(mApplication, t.message)
                }

            })
    }

    //    绑定手机
    fun bindPhone(openid: String, phone: String, code: String) {
        mModel.bind(openid, phone, code).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.bindSuccess()
                    } else {
                        mRootView.bindFail()
                    }
                    MyToast().makeToast(mApplication, t.message)
                }

            })
    }
}
