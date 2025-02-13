package com.auth.application.exception

class MissingJwtPayloadException(
    payloadMissed: String,
) : AuthBaseException(AuthErrorCode.MISSING_JWT_PAYLOAD, payloadMissed)

class MemberNotFoundByEmailException : AuthBaseException(AuthErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)