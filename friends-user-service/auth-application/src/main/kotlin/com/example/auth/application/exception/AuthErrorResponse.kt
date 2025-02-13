package com.example.auth.application.exception

class AuthErrorResponse private constructor(
    val errorCode: Int,
    val errorMessage: String
) {
    companion object {
        fun of(
            errorCode: com.example.auth.application.exception.AuthErrorCode,
            errorMessage: String?,
        ) = com.example.auth.application.exception.AuthErrorResponse(
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