package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.NewFriendBean
import com.diwaves.news.bean.YuCeBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.NewFriendContract
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
 * Created by MVPArmsTemplate on 10/30/2020 17:10
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class NewFriendPresenter
@Inject
constructor(model: NewFriendContract.Model, rootView: NewFriendContract.View) :
    BasePresenter<NewFriendContract.Model, NewFriendContract.View>(model, rootView) {
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

    fun getNewFriendDat(){
        mModel.getData(SPToll(mApplication).getId()).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<NewFriendBean>(mErrorHandler) {
                override fun onNext(t: NewFriendBean) {
                    if (t.code.toString().equals(Api.SUCCESS)) {
                        mRootView.getDataSuccess(t.result)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
                }
            })
    }

    fun okAndNo(type : String){
        mModel.okAndNo(SPToll(mApplication).getId(),type).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    if (t.code.toString().equals(Api.SUCCESS)) {
                      getNewFriendDat()
                    }
                        MyToast().makeToast(mApplication, t.message)

                }
            })
    }
}
