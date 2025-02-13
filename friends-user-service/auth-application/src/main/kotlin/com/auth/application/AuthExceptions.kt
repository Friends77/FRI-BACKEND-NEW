package com.auth.application

import com.example.common.exception.BaseException
import com.example.common.exception.ErrorCode

abstract class AuthException(
    errorCode: ErrorCode,
    vararg args: Any,
) : BaseException(errorCode, *args)

class MissingJwtPayloadException(
    payloadMissed: String,
) : AuthException(ErrorCode.MISSING_JWT_PAYLOAD, payloadMissed)

class InvalidJwtTokenException : AuthException(ErrorCode.INVALID_JWT_TOKEN)

class InvalidNicknameException : AuthException(ErrorCode.INVALID_NICKNAME)

class InvalidEmailException : AuthException(ErrorCode.INVALID_EMAIL)

class InvalidPasswordException : AuthException(ErrorCode.INVALID_PASSWORD)

class EmailAuthenticationFailedException : AuthException(ErrorCode.EMAIL_AUTHENTICATION_FAILED)

class InvalidTokenTypeException : AuthException(ErrorCode.INVALID_TOKEN_TYPE)

class EmailAlreadyExistsException : AuthException(ErrorCode.EMAIL_ALREADY_EXISTS)