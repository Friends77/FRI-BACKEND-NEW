package com.example.common.exception

abstract class BaseException(
    val errorCode: ErrorCode,
    vararg args: Any,
): RuntimeException(errorCode.formatErrorMessage(*args))