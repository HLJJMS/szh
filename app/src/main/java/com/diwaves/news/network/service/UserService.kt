package com.diwaves.news.network.service

import com.diwaves.news.bean.*
import com.diwaves.news.network.Api
import com.diwaves.news.network.bean.BaseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    //用户信息
    @FormUrlEncoded
    @POST(Api.USER_INFO)
    fun getUserInfo(
        @Field("myuserid") myuserid: String, @Field("userid") userid: String
    ): Observable<BaseBean.BaseResponse<MyInfoBean>>

    //用户信息(编辑)
    @POST(Api.USER_EDIT)
    fun postUserInfo(
        @Body body: RequestBody
    ): Observable<BaseBean.BaseResponse<String>>

    //朋友列表
    @FormUrlEncoded
    @POST(Api.FRIEND_LIST)
    fun getFriendList(
        @Field("userid") userid: String
    ): Observable<FriendListBean>

    //搜索朋友
    @FormUrlEncoded
    @POST(Api.SEARCH_FRIEND)
    fun searchFriendList(
        @Field("userid") userid: String, @Field("searchKey") searchKey: String
    ): Observable<FriendListBean>


    //  收藏列表
    @FormUrlEncoded
    @POST(Api.MY_COLLECTION)
    fun getCollectionList(
        @Field("userid") id: String, @Field("current") current: String, @Field("size") size: String
    ): Observable<CollectBean>

    //  评论列表
    @FormUrlEncoded
    @POST(Api.MY_COMMENT)
    fun getMyCnmment(
        @Field("userid") id: String, @Field("current") current: String, @Field("size") size: String
    ): Observable<MyCommentBean>


    //  评论列表
    @FormUrlEncoded
    @POST(Api.MY_FOCUS)
    fun getFansAndLook(
        @Field("userid") id: String, @Field("type") type: String
    ): Observable<FriendListBean>

    //  文章或草稿
    @FormUrlEncoded
    @POST(Api.ARTICLE_MY)
    fun getMyArticle(
        @Field("userid") id: String, @Field("state") state: String
    ): Observable<MyArticleBean>


    //  预测列表
    @FormUrlEncoded
    @POST(Api.MY_YUCE)
    fun getMyYuCe(
        @Field("userid") id: String
    ): Observable<YuCeBean>

    //  支付
    @FormUrlEncoded
    @POST(Api.WXPAY)
    fun wxPay(
        @Field("userid") id: String, @Field("count") count: String
    ): Observable<PayBean>

    //  绑手机
    @FormUrlEncoded
    @POST(Api.WXBINDUSER)
    fun wxBindUser(
        @Field("openid") openid: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<String>>



    //  绑手机
    @FormUrlEncoded
    @POST(Api.EDIT_PHONE)
    fun editPhone(
        @Field("userid") userid: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<String>>

}