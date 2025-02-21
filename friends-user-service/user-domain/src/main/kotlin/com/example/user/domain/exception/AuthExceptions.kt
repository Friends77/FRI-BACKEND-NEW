package com.example.user.domain.exception


class MissingJwtPayloadException(message: String) : BaseException(ErrorCode.MISSING_JWT_PAYLOAD, message)

class InvalidEmailJwtException : BaseException(ErrorCode.INVALID_EMAIL_JWT)

class EmailAlreadyExistsException : BaseException(ErrorCode.EMAIL_ALREADY_EXISTS)

class InvalidEmailPatternException : BaseException(ErrorCode.INVALID_EMAIL_PATTERN)

class InvalidNicknamePatternException : BaseException(ErrorCode.INVALID_NICKNAME_PATTERN)

class InvalidPasswordPatternException : BaseException(ErrorCode.INVALID_PASSWORD_PATTERN)

class LoginFailedException(message: String) : BaseException(ErrorCode.LOGIN_FAILED, message)

class EmailSendFailedException : BaseException(ErrorCode.EMAIL_SEND_FAILED)

class InvalidEmailVerificationCodeException : BaseException(ErrorCode.INVALID_EMAIL_VERIFICATION_CODE)

class OAuth2FetchFailedException(message: String) : BaseException(ErrorCode.OAUTH2_FETCH_FAILED, message)

class AlreadyRegisteredAnotherMethodException : BaseException(ErrorCode.ALREADY_REGISTERED_ANOTHER_METHOD)

class InvalidOAuth2ProviderException : BaseException(ErrorCode.INVALID_OAUTH2_PROVIDER)

class OAuth2DataExtractException : BaseException(ErrorCode.OAUTH2_DATA_EXTRACT_FAILED)

class InvalidRefreshTokenException : BaseException(ErrorCode.INVALID_REFRESH_TOKEN)

class OAuth2UserPasswordChangeException(message : String? = null) : BaseException(ErrorCode.OAUTH2_USER_PASSWORD_CHANGE, message)

class PasswordEqualLastPasswordException : BaseException(ErrorCode.PASSWORD_EQUAL_LAST_PASSWORD)

class IllegalMemberArgumentException(message : String) : BaseException(ErrorCode.ILLEGAL_MEMBER_ARGUMENT, message)