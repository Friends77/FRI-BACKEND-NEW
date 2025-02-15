package com.example.user.domain.exception

abstract class BaseException (
    val errorCode: ErrorCode,
    vararg args: Any,
) : RuntimeException(errorCode.formatErrorMessage(*args))
