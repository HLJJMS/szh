package com.diwaves.news.bean

data class YuCeCommentUpBean(
    var chuang: Chuang,
    var shang: Shang,
    var sheng: Sheng
)

data class Chuang(
    var change_pct: Double,
    var current: String,
    var date: String,
    var name: String,
    var percentage: String,
    var predict: Predict
)

data class Shang(
    var change_pct: Double,
    var current: String,
    var date: String,
    var name: String,
    var percentage: String,
    var predict: PredictX
)

data class Sheng(
    var change_pct: Double,
    var current: String,
    var date: String,
    var name: String,
    var percentage: String,
    var predict: PredictXX
)

data class Predict(
    var dirid: Int,
    var enddatetime: String,
    var id: Int,
    var option1: String,
    var option1value: Int,
    var option2: String,
    var option2value: Int,
    var option3: String,
    var option3value: Int,
    var option4: String,
    var option4value: Int,
    var option5: String,
    var option5value: Int,
    var predate: String,
    var preresulttext: String,
    var preup: Int,
    var result: Int,
    var settlebegintime: String,
    var settleendtime: String,
    var state: Int,
    var tableid: Int,
    var title: String,
    var type: String,
    var zstype: Int
)

data class PredictX(
    var dirid: Int,
    var enddatetime: String,
    var id: Int,
    var option1: String,
    var option1value: Int,
    var option2: String,
    var option2value: Int,
    var option3: String,
    var option3value: Int,
    var option4: String,
    var option4value: Int,
    var option5: String,
    var option5value: Int,
    var predate: String,
    var preresulttext: String,
    var preup: Int,
    var result: Int,
    var settlebegintime: String,
    var settleendtime: String,
    var state: Int,
    var tableid: Int,
    var title: String,
    var type: String,
    var zstype: Int
)

data class PredictXX(
    var dirid: Int,
    var enddatetime: String,
    var id: Int,
    var option1: String,
    var option1value: Int,
    var option2: String,
    var option2value: Int,
    var option3: String,
    var option3value: Int,
    var option4: String,
    var option4value: Int,
    var option5: String,
    var option5value: Int,
    var predate: String,
    var preresulttext: String,
    var preup: Int,
    var result: Int,
    var settlebegintime: String,
    var settleendtime: String,
    var state: Int,
    var tableid: Int,
    var title: String,
    var type: String,
    var zstype: Int
)


data class YuCeDetail(
    var d: D,
    var m: M,
    var w: W
)

data class D(
    var predict: Predict,
    var predictStatus: String,
    var silver: Int,
    var userpredictList: List<Userpredict>
)

data class M(
    var predict: PredictX,
    var predictStatus: String,
    var silver: Int,
    var userpredictList: List<Userpredict>
)

data class W(
    var predict: PredictXX,
    var predictStatus: String,
    var silver: Int,
    var userpredictList: List<Userpredict>
)

data class Userpredict(
    var avatarUrl: String,
    var comment: String,
    var createdatetime: String,
    var hide: Int,
    var id: Int,
    var name: String,
    var option: Int,
    var optionvalue: Any,
    var predictid: Int,
    var result: Int,
    var silver: Int,
    var silverwin: Int,
    var state: Int,
    var title: Any,
    var up: Int,
    var upStatus: Any,
    var userid: Int,
    var zstype: Int
)
