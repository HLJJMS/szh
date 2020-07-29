package com.diwaves.news.mvp.presenter

import android.app.Application
import com.diwaves.news.bean.ArticleDetailBean
import com.diwaves.news.bean.CommentBean

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import com.diwaves.news.mvp.contract.ArticleDetailContract
import com.diwaves.news.network.Api
import com.diwaves.news.network.RxUtils
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.tools.MyToast
import com.diwaves.news.tools.SPToll
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import okhttp3.MultipartBody


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

    fun addComment(body: MultipartBody) {
        mModel.addComment(body).compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.commentSuccess()
                    }
                    MyToast().makeToast(mApplication, t.message)
                }
            })
    }

    fun getComment(articleid: String, current: Int, type: String) {
        mModel.getComment(SPToll(mApplication).getId(), articleid, current.toString(), type)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<CommentBean>(mErrorHandler) {
                override fun onNext(t: CommentBean) {
                    if (t.code.equals(Api.SUCCESS)) {
                        if (t.result.records.size == 0) {
                            mRootView.getCommentListNull()
                        } else {
                            mRootView.getCommentListSuccess(t.result.records)
                        }

                    } else {
                        mRootView.getCommentListFail()
                        MyToast().makeToast(mApplication, t.message)
                    }

                }
            })
    }


    fun goodComment(articleid: String, commentId: String, type: String) {
        mModel.goodComment(SPToll(mApplication).getId(), articleid, commentId, type)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.commentSuccess()
                    }
                    MyToast().makeToast(mApplication, t.message)

                }
            })
    }

    fun commentReplay(articleid: String, commentId: String, type: String) {
        mModel.commentReplay(SPToll(mApplication).getId(), articleid, commentId, type)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<Any>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<Any>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.commentSuccess()
                    }
                    MyToast().makeToast(mApplication, t.message)

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

    fun sorce(articleid: String, sorce: String) {
        mModel.sorce(SPToll(mApplication).getId(), sorce, articleid)
            .compose(RxUtils.applySchedulers(mRootView))
            .subscribe(object :
                ErrorHandleSubscriber<BaseBean.BaseResponse<String>>(mErrorHandler) {
                override fun onNext(t: BaseBean.BaseResponse<String>) {
                    if (t.code.equals(Api.SUCCESS)) {
                        mRootView.commentSuccess()
                    }
                    MyToast().makeToast(mApplication, t.message)

                }
            })
    }
}
