package com.diwaves.news.network.bean

import java.io.Serializable

class BaseBean {
    data class BaseResponse<T>(
        var result: T? = null,
        var code: String = "",
        var message: String = "",
        var success: String = "",
        var timestamp: String = ""
    ) :
        Serializable {
        override fun toString(): String {
            return "BaseResponse{" +
                    "code='" + code + '\''.toString() +
                    ", message='" + message + '\''.toString() +
                    ",success='" + success + '\''.toString() +
                    ",timestamp='" + timestamp + '\''.toString() +
                    '}'.toString()
        }
    }
}