package com.example.szh.network.service

import com.example.szh.bean.*
import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

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


}