package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.eventbus.MainEvent

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.ResealesPhotoActivityContract
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 11/16/2020 20:38
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class ResealesPhotoActivityPresenter
@Inject
constructor(
    model: ResealesPhotoActivityContract.Model,
    rootView: ResealesPhotoActivityContract.View
) :
    BasePresenter<ResealesPhotoActivityContract.Model, ResealesPhotoActivityContract.View>(
        model,
        rootView
    ) {
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

    fun postImage(body: RequestBody) {
        mModel.postPhoto(body).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals("200")) {
                        mRootView.postPhotoSuccess(t.message)
                    }else{
                        MyToast().makeToast(mApplication, t.message)
                    }

                }
            })
    }

    fun postArticle(
        audiopath: String,
        contenttext: String,
        state: String,
        title: String
    ) {
        mModel.postArticle(SPToll(mApplication).getId(),audiopath,contenttext,state,title).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    if (t.code.equals("200")) {
                        EventBus.getDefault().post(MainEvent(2));
                        mRootView.killMyself()
                    }
                    MyToast().makeToast(mApplication, t.message)
                }
            })
    }
}
