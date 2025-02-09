package com.example.friendservernew.common.exception

abstract class CustomException(
    val errorCode: ErrorCode,
    vararg args: Any,
) : RuntimeException(errorCode.formatErrorMessage(*args))
