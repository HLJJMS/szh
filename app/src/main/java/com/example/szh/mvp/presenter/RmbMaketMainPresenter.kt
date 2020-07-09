package com.example.szh.mvp.presenter

import android.app.Application
import com.example.szh.bean.CommentBean
import com.example.szh.bean.RmbMaketBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.example.szh.mvp.contract.RmbMaketMainContract
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

    fun getData(dirid: String, type: String) {
        mModel.getData(SPToll(mApplication).getId(), dirid, type).compose(
            RxUtils.applySchedulers(mRootView)
        )
            .subscribe(object :
                ErrorHandleSubscriber<RmbMaketBean>(mErrorHandler) {
                override fun onNext(t: RmbMaketBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.success(t.result)
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
