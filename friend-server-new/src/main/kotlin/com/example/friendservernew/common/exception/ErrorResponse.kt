package com.example.friendservernew.common.exception

class ErrorResponse private constructor(
    val code: Int,
    val errorMessage: String,
) {
    companion object {
        fun of(
            errorCode: ErrorCode,
            errorMessage: String?,
        ) = ErrorResponse(errorCode.code, errorMessage ?: errorCode.formatErrorMessage())
    }

    fun toJson() =
        """{
        "code": $code,
        "errorMessage": "$errorMessage"
    }"""
}