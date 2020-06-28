package com.example.szh.network.service

import com.example.szh.bean.ArticleDetailBean
import com.example.szh.bean.BangdanBean
import com.example.szh.bean.RecommendBean
import com.example.szh.network.Api
import com.example.szh.network.bean.BaseBean
import com.example.szh.network.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeService {



    //    首页推进
    @FormUrlEncoded
    @POST(Api.RECOMMENDED_LIST)
    fun getHomeRecommended(
        @Field("userid") id: String
    ): Observable<RecommendBean>
    //榜单列表

    @POST(Api.SELECT_DIRECTORY)
    fun getBangdan(): Observable<BangdanBean>



    //    文章详情
    @FormUrlEncoded
    @POST(Api.ARTICLE_DETAIL)
    fun getArticleDetail(
        @Field("userid") id: String, @Field("articleid ") articleid : String, @Field("pushid ") pushid : String
    ): Observable<ArticleDetailBean>

    //  每日取钱
    @FormUrlEncoded
    @POST(Api.EVERYDAY_AG)
    fun getEveryDayAg(
        @Field("userid") id: String
    ): Observable<BaseBean.BaseResponse<String>>
}