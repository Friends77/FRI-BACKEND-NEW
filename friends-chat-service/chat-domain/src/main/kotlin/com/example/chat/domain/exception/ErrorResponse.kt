package com.example.chat.domain.exception

class ErrorResponse private constructor(
    val errorCode: String,
    val errorMessage: String
) {
    companion object {
        fun of(
            errorCode: ErrorCode,
            errorMessage: String?,
        ) = ErrorResponse(
            errorCode.name,
            errorMessage ?: errorCode.formatErrorMessage()
        )
    }
}