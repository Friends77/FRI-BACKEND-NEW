package com.example.auth.application.exception

class MissingJwtPayloadException(
    payloadMissed: String,
) : AuthBaseException(AuthErrorCode.MISSING_JWT_PAYLOAD, payloadMissed)

class MemberNotFoundByEmailException : AuthBaseException(AuthErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)

class EmailAuthenticationFailedException : AuthBaseException(AuthErrorCode.EMAIL_AUTHENTICATION_FAILED)

class InvalidEmailJwtException : AuthBaseException(AuthErrorCode.INVALID_EMAIL_JWT)

class EmailAlreadyExistsException : AuthBaseException(AuthErrorCode.EMAIL_ALREADY_EXISTS)

class InvalidEmailPatternException : AuthBaseException(AuthErrorCode.INVALID_EMAIL_PATTERN)

class InvalidNicknamePatternException : AuthBaseException(AuthErrorCode.INVALID_NICKNAME_PATTERN)

class InvalidPasswordPatternException : AuthBaseException(AuthErrorCode.INVALID_PASSWORD_PATTERN)