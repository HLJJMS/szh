package com.example.szh.network.service

import com.example.szh.bean.RecommendBean
import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HomeService {
    //注册
    @FormUrlEncoded
    @POST(Api.RECOMMENDED_LIST)
    fun getHomeRecommended(
        @Field("userid") id: String
    ): Observable<BaseBean.BaseResponse<List<RecommendBean>>>
}