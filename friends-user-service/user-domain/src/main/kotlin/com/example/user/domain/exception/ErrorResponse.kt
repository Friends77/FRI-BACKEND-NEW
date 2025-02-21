package com.example.user.domain.exception

class ErrorResponse(
    errorCode: ErrorCode,
    errorMessage: String?
) {
    val errorCode: String = errorCode.name
    val errorMessage: String = errorMessage ?: errorCode.defaultMessage
}