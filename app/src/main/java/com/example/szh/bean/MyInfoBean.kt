package com.example.szh.bean

data class MyInfoUserBean(
    var id: String,
    var name: String,
    var phone: String,
    var unionId: String,
    var wxname: String,
    var openId: String,
    var avatarUrl: String,
    var country: String,
    var province: String,
    var city: String,
    var gender: String,
    var birthday: String,
    var level: String,
    var fans: String,
    var focus: String,
    var friends: String,
    var password: String,
    var gold: String,
    var silver: String,
    var createscore: String,
    var predictscore: String,
    var predictcount: String,
    var expire_ttl: String,
    var lastlogintime: String,
    var createlevel: String,
    var predictlevel: String,
    var verificaCode: String
)

data class MyInfoBean(
    var pingbi: String,
    var quanxian: String,
    var yuce: String,
    var tuijian: String,
    var pinlun: String,
    var shoucang: String,
    var tiezi: String,
    var chufa: String,
    var viewweekcount: String,
    var viewdaycount: String,
    var viewmonthcount: String,
    var caogaoxiang: String,
    var user: MyInfoUserBean
)