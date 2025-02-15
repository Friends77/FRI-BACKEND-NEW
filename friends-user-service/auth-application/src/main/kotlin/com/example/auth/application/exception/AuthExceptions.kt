package com.example.auth.application.exception

import com.example.user.domain.valueobject.OAuth2Provider

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

class LoginFailedException : AuthBaseException(AuthErrorCode.LOGIN_FAILED)

class EmailSendFailedException : AuthBaseException(AuthErrorCode.EMAIL_SEND_FAILED)

class InvalidEmailVerificationCodeException : AuthBaseException(AuthErrorCode.INVALID_EMAIL_VERIFICATION_CODE)

class OAuth2FetchFailedException(oAuth2Provider: OAuth2Provider) : AuthBaseException(AuthErrorCode.OAUTH2_FETCH_FAILED, oAuth2Provider.name)

class AlreadyRegisteredAnotherMethodException : AuthBaseException(AuthErrorCode.ALREADY_REGISTERED_ANOTHER_METHOD)