package com.example.chat.domain.exception

abstract class BaseException (
    val errorCode: ErrorCode,
    override val message : String? = null,
) : RuntimeException(message ?: errorCode.defaultMessage)
