package com.example.szh.network.service

import com.example.szh.bean.MyInfoBean
import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {
    //钱包framgent
    @FormUrlEncoded
    @POST(Api.USER_INFO)
    fun getUserInfo(
        @Field("userid") id: String
    ): Observable<BaseBean.BaseResponse<MyInfoBean>>

}