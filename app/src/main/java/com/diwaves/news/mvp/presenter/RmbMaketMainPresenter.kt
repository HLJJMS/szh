package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.KListBean


import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.RmbMaketMainContract
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
 * Created by MVPArmsTemplate on 07/09/2020 11:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class RmbMaketMainPresenter
@Inject
constructor(model: RmbMaketMainContract.Model, rootView: RmbMaketMainContract.View) :
    BasePresenter<RmbMaketMainContract.Model, RmbMaketMainContract.View>(model, rootView) {
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

    fun getData(type: String) {
        mModel.getData(SPToll(mApplication).getId(), type).compose(
            RxUtils.applySchedulers(mRootView)
        )
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<KListBean>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<KListBean>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        t.result?.let { mRootView.success(it) }
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }

                }
            })
    }


    fun postData(
        silver: String,
        predictid: String,
        option: String,
        hide: String
    ) {
        mModel.postData(SPToll(mApplication).getId(), silver, predictid, option, hide).compose(
            RxUtils.applySchedulers(mRootView)
        )
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.addSuccess()
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }

                }
            })
    }
}
