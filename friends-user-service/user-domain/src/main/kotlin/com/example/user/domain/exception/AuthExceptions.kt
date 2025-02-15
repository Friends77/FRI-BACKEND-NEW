package com.example.user.domain.exception

import com.example.user.domain.valueobject.OAuth2Provider

class MissingJwtPayloadException(
    payloadMissed: String,
) : BaseException(ErrorCode.MISSING_JWT_PAYLOAD, payloadMissed)

class InvalidEmailJwtException : BaseException(ErrorCode.INVALID_EMAIL_JWT)

class EmailAlreadyExistsException : BaseException(ErrorCode.EMAIL_ALREADY_EXISTS)

class InvalidEmailPatternException : BaseException(ErrorCode.INVALID_EMAIL_PATTERN)

class InvalidNicknamePatternException : BaseException(ErrorCode.INVALID_NICKNAME_PATTERN)

class InvalidPasswordPatternException : BaseException(ErrorCode.INVALID_PASSWORD_PATTERN)

class LoginFailedException : BaseException(ErrorCode.LOGIN_FAILED)

class EmailSendFailedException : BaseException(ErrorCode.EMAIL_SEND_FAILED)

class InvalidEmailVerificationCodeException : BaseException(ErrorCode.INVALID_EMAIL_VERIFICATION_CODE)

class OAuth2FetchFailedException(oAuth2Provider: OAuth2Provider) : BaseException(ErrorCode.OAUTH2_FETCH_FAILED, oAuth2Provider.name)

class AlreadyRegisteredAnotherMethodException : BaseException(ErrorCode.ALREADY_REGISTERED_ANOTHER_METHOD)

class InvalidOAuth2ProviderException : BaseException(ErrorCode.INVALID_OAUTH2_PROVIDER)

class OAuth2DataExtractException : BaseException(ErrorCode.OAUTH2_DATA_EXTRACT_FAILED)