package com.example.szh.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.example.szh.mvp.contract.PushTieContract
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
 * Created by MVPArmsTemplate on 06/10/2020 17:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class PushTiePresenter
@Inject
constructor(model: PushTieContract.Model, rootView: PushTieContract.View) :
    BasePresenter<PushTieContract.Model, PushTieContract.View>(model, rootView) {
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

    fun postData(peoplecount: String, type: String, articleid: String) {
        mModel.postData(SPToll(mApplication).getId(), peoplecount, type, articleid)
            .compose(RxUtils.applySchedulers(mRootView)).safeSubscribe(object :
            ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
            override fun onNext(t: BaseBean.BaseResponse<Any>) {
                if (t.code.equals(Api.SUCCESS)) {
                    mRootView.success()
                }
                MyToast().makeToast(mApplication, t.message)
            }

        })
    }
}
