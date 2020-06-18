package com.example.szh.network.service

import com.example.szh.bean.MyInfoBean
import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface UserService {
    //用户信息
    @FormUrlEncoded
    @POST(Api.USER_INFO)
    fun getUserInfo(
        @Field("userid") id: String
    ): Observable<BaseBean.BaseResponse<MyInfoBean>>

    //用户信息(编辑)
    @FormUrlEncoded
    @POST(Api.USER_EDIT)
    fun postUserInfo(
        @Body body: MultipartBody
    ): Observable<BaseBean.BaseResponse<String>>

}