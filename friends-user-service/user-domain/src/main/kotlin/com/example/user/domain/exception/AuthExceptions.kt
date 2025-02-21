package com.example.user.domain.exception


class MissingJwtPayloadException(message: String) : BaseException(ErrorCode.MISSING_JWT_PAYLOAD, message)

class InvalidEmailJwtException : BaseException(ErrorCode.INVALID_EMAIL_JWT)

class LoginFailedException(message: String) : BaseException(ErrorCode.LOGIN_FAILED, message)

class EmailSendFailedException : BaseException(ErrorCode.EMAIL_SEND_FAILED)

class InvalidEmailVerificationCodeException : BaseException(ErrorCode.INVALID_EMAIL_VERIFICATION_CODE)

class OAuth2FetchFailedException(message: String) : BaseException(ErrorCode.OAUTH2_FETCH_FAILED, message)

class AlreadyRegisteredAnotherMethodException(message: String? = null) : BaseException(ErrorCode.ALREADY_REGISTERED_ANOTHER_METHOD, message)

class OAuth2DataExtractException(message : String? = null) : BaseException(ErrorCode.OAUTH2_DATA_EXTRACT_FAILED, message)

class InvalidRefreshTokenException : BaseException(ErrorCode.INVALID_REFRESH_TOKEN)

class OAuth2UserPasswordChangeException(message : String? = null) : BaseException(ErrorCode.OAUTH2_USER_PASSWORD_CHANGE, message)

class PasswordEqualLastPasswordException : BaseException(ErrorCode.PASSWORD_EQUAL_LAST_PASSWORD)

class IllegalMemberArgumentException(message : String) : BaseException(ErrorCode.ILLEGAL_MEMBER_ARGUMENT, message)