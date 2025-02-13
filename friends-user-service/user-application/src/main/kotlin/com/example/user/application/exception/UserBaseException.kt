package com.example.user.application.exception

abstract class UserBaseException(
    val errorCode: UserErrorCode,
    vararg args: Any,
) : RuntimeException(errorCode.formatErrorMessage(*args))