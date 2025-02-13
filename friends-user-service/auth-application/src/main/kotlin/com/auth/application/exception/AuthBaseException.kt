package com.auth.application.exception

abstract class AuthBaseException(
    val errorCode: AuthErrorCode,
    vararg args: Any,
) : RuntimeException(errorCode.formatErrorMessage(*args))