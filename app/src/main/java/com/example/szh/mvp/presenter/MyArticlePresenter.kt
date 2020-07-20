package com.example.szh.mvp.presenter

import android.app.Application
import com.example.szh.bean.FocusListBean
import com.example.szh.bean.MyArticleBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.example.szh.mvp.contract.MyArticleContract
import com.example.szh.network.Api
import com.example.szh.network.RxUtils
import com.example.szh.tools.MyToast
import com.example.szh.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/20/2020 18:02
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class MyArticlePresenter
@Inject
constructor(model: MyArticleContract.Model, rootView: MyArticleContract.View) :
    BasePresenter<MyArticleContract.Model, MyArticleContract.View>(model, rootView) {
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
        mModel.getData(SPToll(mApplication).getId(),type).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<MyArticleBean>(mErrorHandler) {
                override fun onNext(t: MyArticleBean) {
                    if (t.code.toString().equals(Api.SUCCESS)) {
                        mRootView.getSuccess(t.result.articlesList)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }
}
