package com.diwaves.news.network.service

import com.diwaves.news.bean.CommentBean
import com.diwaves.news.network.Api
import com.diwaves.news.network.bean.BaseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CommentService {
    //新增评论
    @POST(Api.COMMENT_ADD)
    fun addComment(
        @Body body: RequestBody
    ): Observable<BaseBean.BaseResponse<Any>>


    //评论获取
    @FormUrlEncoded
    @POST(Api.COMMENT_GET)
    fun getComment(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("current") current: String,
        @Field("type") type: String
    ): Observable<CommentBean>

    //评论赞
    @FormUrlEncoded
    @POST(Api.COMMENT_GOOD)
    fun goodComment(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("commentid") current: String,
        @Field("type") type: String//0：点赞，1取消
    ): Observable<BaseBean.BaseResponse<Any>>

    //评论回复
    @FormUrlEncoded
    @POST(Api.COMMENT_ADDREPLAY)
    fun commentReplay(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("commentid") current: String,
        @Field("content ") type: String
    ): Observable<BaseBean.BaseResponse<Any>>

}