package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.WellatIndexBean
import com.diwaves.news.bean.YuCeCommentBean
import com.diwaves.news.bean.YuCeCommentUpBean
import com.diwaves.news.bean.YuCeDetail

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.YuCeCommentContract
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
 * Created by MVPArmsTemplate on 12/23/2020 22:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class YuCeCommentPresenter
@Inject
constructor(model: YuCeCommentContract.Model, rootView: YuCeCommentContract.View) :
    BasePresenter<YuCeCommentContract.Model, YuCeCommentContract.View>(model, rootView) {
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

    fun getData(type:String,zstype:String) {
        mModel.getDownData(type,zstype).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<MutableList<YuCeCommentBean>>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<MutableList<YuCeCommentBean>>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        t.result?.let { mRootView.getCommentSuccess(it) }
                    }
                    MyToast().makeToast(mApplication, t.message)
                }

            })
    }

    fun getDataUp(zstype:String) {
        mModel.getUpData(zstype).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<YuCeDetail>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<YuCeDetail>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        t.result?.let { mRootView.getUpSuccess(it) }
                    }
                    MyToast().makeToast(mApplication, t.message)
                }

            })
    }

    fun goodComment(commentId: String, type: String) {
        mModel.goodComment(SPToll(mApplication).getId(), commentId, type)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.goodSuccess()
                    }
//                    MyToast().makeToast(mApplication, t.message)
                    MyToast().makeToast(mApplication, "成功")

                }
            })
    }

    fun pingbi(articleid: String, title: String) {
        mModel.pingbi(SPToll(mApplication).getId(), title, articleid)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.pingbiSuccess()
                    }
                    MyToast().makeToast(mApplication, t.message)

                }
            })
    }
}
