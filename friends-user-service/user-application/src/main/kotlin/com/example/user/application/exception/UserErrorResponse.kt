package com.example.user.application.exception

class UserErrorResponse private constructor(
    val errorCode: Int,
    val errorMessage: String
) {
    companion object {
        fun of(
            errorCode: UserErrorCode,
            errorMessage: String?,
        ) = UserErrorResponse(errorCode.code, errorMessage ?: errorCode.formatErrorMessage())
    }

    fun toJson() =
        """{
        "code": $errorCode,
        "errorMessage": "$errorMessage"
    }"""
}