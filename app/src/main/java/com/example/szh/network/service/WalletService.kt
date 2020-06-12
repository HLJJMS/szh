package com.example.szh.network.service

import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WalletService {
    //钱包framgent
    @FormUrlEncoded
    @POST(Api.WALLETINDEX)
    fun getWalletData(
        @Field("userid") id: String
    ): Observable<BaseBean.BaseResponse<String>>
}