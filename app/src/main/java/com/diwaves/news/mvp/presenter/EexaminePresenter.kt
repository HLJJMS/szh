package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.MessageAuditBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.EexamineContract
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
 * Created by MVPArmsTemplate on 11/12/2020 17:07
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class EexaminePresenter
@Inject
constructor(model: EexamineContract.Model, rootView: EexamineContract.View) :
    BasePresenter<EexamineContract.Model, EexamineContract.View>(model, rootView) {
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
                ErrorHandleSubscriber<MessageAuditBean>(mErrorHandler) {
                override fun onNext(t: MessageAuditBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.seccuse(t.result)
                    }
                    MyToast().makeToast(mApplication, t.message)
                }
            })
    }

    fun setData(id: String, type: String) {
        mModel.setData(SPToll(mApplication).getId(),type,id).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    MyToast().makeToast(mApplication, t.message)
                }
            })
    }
}
