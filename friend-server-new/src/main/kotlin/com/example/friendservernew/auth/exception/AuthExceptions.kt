package com.example.friendservernew.auth.exception

import com.example.friendservernew.common.exception.CustomException
import com.example.friendservernew.common.exception.ErrorCode


abstract class AuthException(
    errorCode: ErrorCode,
    vararg args: Any,
) : CustomException(errorCode, *args)

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
