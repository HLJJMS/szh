package com.example.szh.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.example.szh.mvp.contract.WalletContract
import com.example.szh.network.Api
import com.example.szh.network.RxUtils
import com.example.szh.network.bean.BaseBean
import com.example.szh.tools.MyToast
import com.example.szh.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/03/2020 15:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class WalletPresenter
@Inject
constructor(model: WalletContract.Model, rootView: WalletContract.View) :
    BasePresenter<WalletContract.Model, WalletContract.View>(model, rootView) {
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

    fun getData() {
        mModel.getData(SPToll(mApplication).getId()).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.success()
                    }
                    MyToast().makeToast(mApplication, t.message)
                }

            })
    }


}
