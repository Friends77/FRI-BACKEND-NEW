package com.example.auth.application.exception

class AuthErrorResponse private constructor(
    val errorCode: Int,
    val errorMessage: String
) {
    companion object {
        fun of(
            errorCode: AuthErrorCode,
            errorMessage: String?,
        ) = AuthErrorResponse(
            errorCode.code,
            errorMessage ?: errorCode.formatErrorMessage()
        )
    }

    fun toJson() =
        """{
        "code": $errorCode,
        "errorMessage": "$errorMessage"
    }"""
}