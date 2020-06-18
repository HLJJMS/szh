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
    const val USER_INFO = "setting/user/select"

    //编辑信息
    const val USER_EDIT = "setting/user/edit"
}