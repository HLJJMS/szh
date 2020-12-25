package com.diwaves.news.bean

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

data class MyInfoBeanNew(
    var caogaoxiang: Int,
    var chufa: Int,
    var friendscount: Int,
    var fscount: Int,
    var gzcount: Int,
    var pengyou: Int,
    var pingbi: Int,
    var shoucang: Int,
    var user: User,
    var viewdaycount: Int,
    var viewmonthcount: Int,
    var viewweekcount: Int,
    var yuce: Int
)

data class User(
    var age: Any,
    var avatarUrl: String,
    var birthday: String,
    var city: String,
    var country: String,
    var createlevel: Int,
    var createscore: Int,
    var expire_ttl: Int,
    var fans: Int,
    var focus: Int,
    var friends: Int,
    var gender: String,
    var gold: Int,
    var id: Int,
    var isMember: Int,
    var level: Int,
    var logintime: String,
    var memberEndTime: Any,
    var name: String,
    var openId: String,
    var password: String,
    var phone: String,
    var predictcount: Int,
    var predictlevel: Int,
    var predictscore: Int,
    var province: String,
    var qrcode: Any,
    var silver: Int,
    var state: Int,
    var szhnum: String,
    var unionId: Any,
    var verificaCode: Any,
    var wxname: String
)




