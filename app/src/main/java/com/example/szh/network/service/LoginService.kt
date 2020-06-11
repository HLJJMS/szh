package com.example.szh.network.service

import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST(Api.REGISTERED)
    fun registered(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<String>>

    @FormUrlEncoded
    @POST(Api.VERIFICATION)
    fun getCode(
        @Field("phone") phone: String,
        @Field("verificationType") verificationType : Int
    ): Observable<BaseBean.BaseResponse<String>>

    @FormUrlEncoded
    @POST(Api.LOGIN)
    fun login(
        @Field("loginType") loginType: String,//0手机1密码
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<String>>
}