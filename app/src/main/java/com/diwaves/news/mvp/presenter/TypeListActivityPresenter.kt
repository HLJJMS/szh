package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.TypeListBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.TypeListActivityContract
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
 * Created by MVPArmsTemplate on 07/22/2020 14:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class TypeListActivityPresenter
@Inject
constructor(model: TypeListActivityContract.Model, rootView: TypeListActivityContract.View) :
    BasePresenter<TypeListActivityContract.Model, TypeListActivityContract.View>(model, rootView) {
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

    fun getData(id:String,type:String,page:Int){
        mModel.getData(SPToll(mApplication).getId(),id,type,page.toString(),"100").compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<TypeListBean>(mErrorHandler) {
                override fun onNext(t: TypeListBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.sunccess(t.result.records)
                    } else {
                        MyToast().makeToast(mApplication, t.message)
                    }
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

                    }
                    MyToast().makeToast(mApplication, t.message)

                }
            })
    }

}
