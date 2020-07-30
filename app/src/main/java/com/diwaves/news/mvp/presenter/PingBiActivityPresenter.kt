package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.FriendListBean
import com.diwaves.news.bean.MyArticleBean
import com.diwaves.news.bean.PingBiListBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.PingBiActivityContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/29/2020 16:51
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class PingBiActivityPresenter
@Inject
constructor(model: PingBiActivityContract.Model, rootView: PingBiActivityContract.View) :
    BasePresenter<PingBiActivityContract.Model, PingBiActivityContract.View>(model, rootView) {
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
                ErrorHandleSubscriber<PingBiListBean>(mErrorHandler) {
                override fun onNext(t: PingBiListBean) {
                    if (t.code.toString().equals(Api.SUCCESS)) {
                        mRootView.success(t.result.list.records)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }
}
