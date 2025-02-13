package com.auth.application.exception

class MissingJwtPayloadException(
    payloadMissed: String,
) : AuthBaseException(AuthErrorCode.MISSING_JWT_PAYLOAD, payloadMissed)