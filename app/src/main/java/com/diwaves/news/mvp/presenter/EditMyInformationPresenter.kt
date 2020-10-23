package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.MyInfoBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.EditMyInformationContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.MultipartBody


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
class EditMyInformationPresenter
@Inject
constructor(model: EditMyInformationContract.Model, rootView: EditMyInformationContract.View) :
    BasePresenter<EditMyInformationContract.Model, EditMyInformationContract.View>(
        model,
        rootView
    ) {
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
                ErrorHandleSubscriber<BaseBean.BaseResponse<MyInfoBean>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<MyInfoBean>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        t.result?.let { mRootView.success(it) }
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }

    fun postData(body: MultipartBody) {
        mModel.postData(body).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    MyToast().makeToast(mApplication, t.message)
                }
            })
    }

}
