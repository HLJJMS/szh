package com.diwaves.news.network

object Api {
    const val APP_ID = "wx5a097702a5ad39e9"
    const val BASE_URL = "http://myzhu.zicp.vip:43280/"


    //成功
    const val SUCCESS = "200"

    //    微信登录
    const val WX_LOGIN = "system/wxlogin"

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
    const val EVERYDAY_AG = "index/everyDay/login"

    //关注列表
    const val FOCUS_LIST = "index/focusList"

    //文章喜欢
    const val ARTICLE_LIKE = "article/like"

    //文章收藏
    const val ARTICLE_CLLECTION = "article/collection"

    //首页搜索
    const val INDEX_SEARCH = "index/search"

    //新增评论
    const val COMMENT_ADD = "comment/add"

    //获取文章评论
    const val COMMENT_GET = "comment/select"

    //点赞评论
    const val COMMENT_GOOD = "comment/commentUpOrCancel"

    //预测主页
    const val PREDICT_SELECT = "index/predict/select"

    //预测正价
    const val ADD_PREDICT_SELECT = "index/predict/add"

    //评论回复
    const val COMMENT_ADDREPLAY = "comment/addReply";

    //朋友类表
    const val FRIEND_LIST = "friends/list";

    //朋友类表
    const val SEARCH_FRIEND = "friends/searchFriends";

    //我的收藏
    const val MY_COLLECTION = "my/articles/collection";

    //我的评论
    const val MY_COMMENT = "my/comment/list";

    //我的评论
    const val MY_FOCUS = "my/focus";

    //上传图片
    const val IMAGE_UPLOAD = "imgUpload/upload";

    //发布文章
    const val ARTICLE_RELEASE = "article/release";

    //查询草稿或文章
    const val ARTICLE_MY = "article/my";

    //举报
    const val REPORT_ADD = "report/add";

    //屏蔽
    const val REPORT_PINGBI = "report/shielding";

    //预测
    const val MY_YUCE = "my/userpredict/list";

    //推贴
    const val INDEX_PUSH = "index/push";

    //榜单分类咨询
    const val ZX_LIST = "index/top/zxList";

    //微信支付
    const val WXPAY = "pay/wxpay"

    //微信绑定
    const val WXBINDUSER = "system/wxbinduser"

    //更换手机号
    const val EDIT_PHONE = "system/replacePhone"


}