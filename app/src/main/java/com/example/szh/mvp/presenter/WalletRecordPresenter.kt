package com.example.szh.mvp.presenter

import android.app.Application
import com.example.szh.bean.WelltaRecordBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.example.szh.mvp.contract.WalletRecordContract
import com.example.szh.network.Api
import com.example.szh.network.RxUtils
import com.example.szh.tools.MyToast
import com.example.szh.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/17/2020 11:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class WalletRecordPresenter
@Inject
constructor(model: WalletRecordContract.Model, rootView: WalletRecordContract.View) :
    BasePresenter<WalletRecordContract.Model, WalletRecordContract.View>(model, rootView) {
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

    fun getList(type:String){
        mModel.getData(SPToll(mApplication).getId(),type).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<WelltaRecordBean>(mErrorHandler) {
                override fun onNext(t: WelltaRecordBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.success(t.result)
                    }
                    MyToast().makeToast(mApplication, t.message)
                }

            })
    }
}
