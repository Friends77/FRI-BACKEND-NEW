package com.example.auth.application.exception

class MissingJwtPayloadException(
    payloadMissed: String,
) : AuthBaseException(AuthErrorCode.MISSING_JWT_PAYLOAD, payloadMissed)

class MemberNotFoundByEmailException : AuthBaseException(AuthErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)

class EmailAuthenticationFailedException : AuthBaseException(AuthErrorCode.EMAIL_AUTHENTICATION_FAILED)

class InvalidEmailJwtException : AuthBaseException(AuthErrorCode.INVALID_EMAIL_JWT)