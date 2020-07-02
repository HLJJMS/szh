package com.example.szh.mvp.presenter

import android.app.Application
import com.example.szh.bean.ArticleDetailBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.example.szh.mvp.contract.ArticleDetailContract
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
 * Created by MVPArmsTemplate on 06/27/2020 14:28
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class ArticleDetailPresenter
@Inject
constructor(model: ArticleDetailContract.Model, rootView: ArticleDetailContract.View) :
    BasePresenter<ArticleDetailContract.Model, ArticleDetailContract.View>(model, rootView) {
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

    fun getData(article: String, pushid: String) {
        mRootView.showLoading()
        mModel.getData(SPToll(mApplication).getId(), article, pushid)
            .compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
                ErrorHandleSubscriber<ArticleDetailBean>(mErrorHandler) {
                override fun onNext(t: ArticleDetailBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.getDataSuccess(t.result)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }

                    mRootView.hideLoading()
                }
            })
    }

    fun like(article: String, like: String) {
        mRootView.showLoading()
        mModel.like(SPToll(mApplication).getId(), article, like)
            .compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    MyToast().makeToast(mApplication, t.message)
                    mRootView.hideLoading()
                }

            })
    }

    fun cllection(article: String, cllection: String) {
        mRootView.showLoading()
        mModel.cllection(SPToll(mApplication).getId(), article, cllection)
            .compose(RxUtils.applySchedulers(mRootView)).subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    MyToast().makeToast(mApplication, t.message)
                    mRootView.hideLoading()
                }

            })
    }
}
