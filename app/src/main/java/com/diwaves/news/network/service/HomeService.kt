package com.diwaves.news.network.service

import com.diwaves.news.bean.*
import com.diwaves.news.network.Api
import com.diwaves.news.network.bean.BaseBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface HomeService {
    //    首页推进
    @FormUrlEncoded
    @POST(Api.RECOMMENDED_LIST)
    fun getHomeRecommended(
        @Field("userid") id: String
    ): Observable<RecommendBean>

    //首页股票
    @POST(Api.APP_STOCK)
    fun getSTOCK(): Observable<StockBean>

    //榜单列表

    @POST(Api.SELECT_DIRECTORY)
    fun getBangdan(): Observable<BangdanBean>


    //    文章详情
    @FormUrlEncoded
    @POST(Api.ARTICLE_DETAIL)
    fun getArticleDetail(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("pushid") pushid: String
    ): Observable<ArticleDetailBean>

    //  每日取钱
    @FormUrlEncoded
    @POST(Api.EVERYDAY_AG)
    fun getEveryDayAg(
        @Field("userid") id: String
    ): Observable<BaseBean.BaseResponse<String>>

    //  关注列表
    @FormUrlEncoded
    @POST(Api.FOCUS_LIST)
    fun getFocusList(
        @Field("userid") id: String
    ): Observable<FocusListBean>

    @FormUrlEncoded
        @POST(Api.INDEX_LIST)
        fun getMyList(
            @Field("userid") id: String
        ): Observable<FocusListBean>


    //  喜欢文章 0:喜欢，1:不喜欢
    @FormUrlEncoded
    @POST(Api.ARTICLE_LIKE)
    fun likeArticle(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("like") like: String
    ): Observable<BaseBean.BaseResponse<String>>

    //  收藏 0:ok，1:取消
    @FormUrlEncoded
    @POST(Api.ARTICLE_CLLECTION)
    fun cllectionArticle(
        @Field("userid") id: String,
        @Field("articleid") articleid: String,
        @Field("type") cllection: String
    ): Observable<BaseBean.BaseResponse<String>>


    //  搜索 0用户 1 文章
    @FormUrlEncoded
    @POST(Api.INDEX_SEARCH)
    fun search(
        @Field("userid") id: String,
        @Field("searchKey") searchKey: String,
        @Field("type") cllection: String
    ): Observable<RecommendBean>


    //  预测
    @FormUrlEncoded
    @POST(Api.PREDICT_SELECT)
    fun getPredict(
        @Field("userid") id: String, @Field("dirid") dirid: String, @Field("type") type: String
    ): Observable<RmbMaketBean>


    //  预测add
    @FormUrlEncoded
    @POST(Api.ADD_PREDICT_SELECT)
    fun addPredict(
        @Field("userid") id: String,
        @Field("silver") silver: String,
        @Field("predictid") predictid: String,
        @Field("option") option: String,
        @Field("hide") hide: String
    ): Observable<BaseBean.BaseResponse<String>>

    //文章上传图片
    @POST(Api.IMAGE_UPLOAD)
    fun postPhoto(
        @Body body: RequestBody
    ): Observable<BaseBean.BaseResponse<String>>


    //发布帖子
    @POST(Api.ARTICLE_RELEASE)
    fun addArticle(
        @Body body: RequestBody
    ): Observable<BaseBean.BaseResponse<String>>


    //pingbi
    @FormUrlEncoded
    @POST(Api.REPORT_PINGBI)
    fun pingbi(
        @Field("userid") id: String,
        @Field("title") title: String,
        @Field("articleid") articleid: String
    ): Observable<BaseBean.BaseResponse<String>>

    //发布帖子
    @POST(Api.REPORT_ADD)
    fun jvbao(
        @Body body: RequestBody
    ): Observable<BaseBean.BaseResponse<Any>>

    //tui帖子
    @FormUrlEncoded
    @POST(Api.INDEX_PUSH)
    fun pushTie(
        @Field("userid") id: String,
        @Field("peoplecount") peoplecount: String,
        @Field("type") type: String,
        @Field("articleid") articleid: String
    ): Observable<BaseBean.BaseResponse<Any>>

    //分类查询文章列表
    @FormUrlEncoded
    @POST(Api.ZX_LIST)
    fun getZXList(
        @Field("userid") id: String,
        @Field("dirid") dirid: String,
        @Field("type") type: String,
        @Field("current") articleid: String,
        @Field("size") size: String
    ): Observable<TypeListBean>

    //评分
    @FormUrlEncoded
    @POST(Api.INDEX_PUSH)
    fun sorce(
        @Field("userid") id: String,
        @Field("score") score: String,
        @Field("articleid") articleid: String
    ): Observable<BaseBean.BaseResponse<String>>
}