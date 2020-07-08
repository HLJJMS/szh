package com.example.szh.network.service

import com.example.szh.adapter.CommentAdapter
import com.example.szh.bean.CommentBean
import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

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

    //评论获取
    @FormUrlEncoded
    @POST(Api.COMMENT_GOOD)
    fun goodComment(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("commentid") current: String,
        @Field("type") type: String//0：点赞，1取消
    ): Observable<BaseBean.BaseResponse<Any>>
}