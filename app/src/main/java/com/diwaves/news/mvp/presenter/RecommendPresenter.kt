package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.RecommendBean
import com.diwaves.news.bean.StockBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.RecommendContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/19/2020 14:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class RecommendPresenter
@Inject
constructor(model: RecommendContract.Model, rootView: RecommendContract.View) :
    BasePresenter<RecommendContract.Model, RecommendContract.View>(model, rootView) {
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

    fun getData(){
        mModel.getData(SPToll(mApplication).getId()).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<RecommendBean>(mErrorHandler) {
                override fun onNext(t: RecommendBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.success(t.result)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }

    fun getStockData(){
        mModel.getStockData().compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<StockBean>(mErrorHandler) {
                override fun onNext(t: StockBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.StockSuccess(t.result)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }
}
