package com.example.user.domain.exception

abstract class BaseException (
    val errorCode: ErrorCode,
    override val message : String? = null,
) : RuntimeException(message ?: errorCode.defaultMessage)
