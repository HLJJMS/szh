package com.example.szh.network.bean

import java.io.Serializable

class BaseBean {
    data class BaseResponse<T>(
        var data: T? = null,
        var code: String = "",
        var msg: String = "",
        var success: String = "",
        var timestamp: String = ""
    ) :
        Serializable {
        override fun toString(): String {
            return "BaseResponse{" +
                    "code='" + code + '\''.toString() +
                    ", msg='" + msg + '\''.toString() +
                    ",success='" + success + '\''.toString() +
                    ",timestamp='" + timestamp + '\''.toString() +
                    '}'.toString()
        }
    }
}