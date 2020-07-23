package com.diwaves.news.network.service

import com.diwaves.news.network.Api
import com.diwaves.news.network.bean.BaseBean
import com.diwaves.news.network.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    //注册
    @FormUrlEncoded
    @POST(Api.REGISTERED)
    fun registered(
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<String>>

    //0登录，1注册，2重置
    @FormUrlEncoded
    @POST(Api.VERIFICATION)
    fun getCode(
        @Field("phone") phone: String,
        @Field("verificationType") verificationType : Int
    ): Observable<BaseBean.BaseResponse<String>>

    //登录
    @FormUrlEncoded
    @POST(Api.LOGIN)
    fun login(
        @Field("loginType") loginType: String,//0手机1密码
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<LoginBean.Login>>

    //重置密码
    @FormUrlEncoded
    @POST(Api.RESETPASSWORD)
    fun resetPassword(
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("verificaCode") verificaCode: String
    ): Observable<BaseBean.BaseResponse<String>>
}