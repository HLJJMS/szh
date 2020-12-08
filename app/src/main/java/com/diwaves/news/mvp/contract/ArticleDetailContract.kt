package com.diwaves.news.mvp.contract

import com.diwaves.news.bean.ArticleDetailBean
import com.diwaves.news.bean.CommentBean
import com.diwaves.news.bean.ShareBean
import com.diwaves.news.network.bean.BaseBean
import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel
import io.reactivex.Observable
import okhttp3.MultipartBody
import java.text.FieldPosition


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
interface ArticleDetailContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView {
        fun getDataSuccess(bean: ArticleDetailBean.ResultBean)
        fun commentSuccess()
        fun getCommentListNull()
        fun getCommentListFail()
        fun getCommentListSuccess(bean: MutableList<CommentBean.ResultBean.RecordsBean>)
        fun pingbiSuccess()
        fun getSorce(fen: String, position: String)
        fun shareSuccess(bean: ShareBean, firend: Boolean)
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel {
        fun getData(
            id: String, articleid: String, pushid: String
        ): Observable<ArticleDetailBean>

        fun like(
            id: String, articleid: String, like: String
        ): Observable<BaseBean.BaseResponse<String>>

        fun cllection(
            id: String, articleid: String, cllection: String
        ): Observable<BaseBean.BaseResponse<String>>

        fun addComment(
            body: MultipartBody
        ): Observable<BaseBean.BaseResponse<Any>>

        fun getComment(
            id: String, articleid: String, current: String, type: String
        ): Observable<CommentBean>


        fun goodComment(
            id: String, articleid: String, commentid: String, type: String
        ): Observable<BaseBean.BaseResponse<Any>>

        fun commentReplay(
            id: String, articleid: String, commentid: String, neirong: String
        ): Observable<BaseBean.BaseResponse<Any>>

        fun pingbi(
            id: String, title: String, articleid: String
        ): Observable<BaseBean.BaseResponse<String>>

        fun sorce(
            id: String,
            sorce: String,
            articleid: String
        ): Observable<BaseBean.BaseResponse<String>>

        fun getShare(
            id: String
        ): Observable<BaseBean.BaseResponse<ShareBean>>
    }
}
