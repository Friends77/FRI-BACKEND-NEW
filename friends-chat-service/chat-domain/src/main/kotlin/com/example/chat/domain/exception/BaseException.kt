package com.example.chat.domain.exception

abstract class BaseException (
    val errorCode: ErrorCode,
    vararg args: Any,
) : RuntimeException(errorCode.formatErrorMessage(*args))
