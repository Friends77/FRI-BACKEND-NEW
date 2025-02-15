package com.example.user.domain.exception

class ErrorResponse private constructor(
    val errorCode: Int,
    val errorMessage: String
) {
    companion object {
        fun of(
            errorCode: ErrorCode,
            errorMessage: String?,
        ) = ErrorResponse(
            errorCode.code,
            errorMessage ?: errorCode.formatErrorMessage()
        )
    }
}