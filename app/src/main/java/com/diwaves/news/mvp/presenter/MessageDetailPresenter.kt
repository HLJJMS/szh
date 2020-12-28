package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.ChartBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.MessageDetailContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.tools.MyToast
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:24
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class MessageDetailPresenter
@Inject
constructor(model: MessageDetailContract.Model, rootView: MessageDetailContract.View) :
    BasePresenter<MessageDetailContract.Model, MessageDetailContract.View>(model, rootView) {
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

    fun getData(id: String) {
        mRootView.showLoading()
        mModel.getData(id).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<MutableList<ChartBean>>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<MutableList<ChartBean>>) {
                    mRootView.hideLoading()
                    if (t.code.toString().equals(Api.SUCCESS)) {
                        t.result?.let { mRootView.getDataSuccess(it) }
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }


    fun sendMessage(id: String, message: String) {
        if(!message.equals("")){
            mModel.sendMessage(id, message).compose(RxUtils.applySchedulers(mRootView))
                .subscribe(object :
                    ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                    override fun onNext(t: BaseBean.BaseResponse<Any>) {
                        if (t.code.toString().equals(Api.SUCCESS)) {
                            getData(id)
                        } else {
                            MyToast().makeToast(mApplication, t.message)
                        }
                    }
                })
        }




    }
}
