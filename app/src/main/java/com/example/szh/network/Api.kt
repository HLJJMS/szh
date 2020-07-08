package com.example.szh.network

object Api {

    const val BASE_URL = "http://myzhu.zicp.vip:43280/"

    //成功
    const val SUCCESS = "200"

    //登录
    const val LOGIN = "system/login"

    //注册
    const val REGISTERED = "system/registered"

    //获取验证码
    const val VERIFICATION = "verification/send"

    //重置密码
    const val RESETPASSWORD = "system/resetPassword"

    //钱包首页
    const val WALLETINDEX = "wallet/index"

    //    交易记录
    const val WALLET_TRADINGRECORD = "wallet/tradingRecord"

    //查询用户
    const val USER_INFO = "setting/user/detail"

    //编辑信息
    const val USER_EDIT = "setting/user/edit"

    //首页推荐
    const val RECOMMENDED_LIST = "index/recommended/list"

    //获取榜单列表
    const val SELECT_DIRECTORY = "article/select/directory"

    //文章详情
    const val ARTICLE_DETAIL = "article/detail"

    //获取榜单列表
    const val EVERYDAY_AG= "index/everyDay/login"

    //关注列表
    const val FOCUS_LIST ="index/focusList"

    //文章喜欢
    const val ARTICLE_LIKE ="article/like"

    //文章收藏
    const val ARTICLE_CLLECTION ="article/collection"

    //首页搜索
    const val INDEX_SEARCH ="index/search"

    //新增评论
    const val COMMENT_ADD ="comment/add"

    //获取文章评论
    const val COMMENT_GET = "comment/select"

    //点赞评论
    const val COMMENT_GOOD = "comment/commentUpOrCancel"


}