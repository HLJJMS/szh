package com.diwaves.news.network.service

import com.diwaves.news.bean.WellatIndexBean
import com.diwaves.news.bean.WelltaRecordBean
import com.diwaves.news.network.Api
import com.diwaves.news.network.bean.BaseBean
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
    ): Observable<BaseBean.BaseResponse<WellatIndexBean>>

    //钱包framgent
    @FormUrlEncoded
    @POST(Api.WALLET_TRADINGRECORD)
    fun getWalletRecord(
        @Field("userid") id: String, @Field("cointype") cointype: String
    ): Observable<WelltaRecordBean>

}