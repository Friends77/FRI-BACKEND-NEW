package com.example.auth.application.exception

import org.springframework.http.HttpStatus

/**
 * 10000 ~ 10999 : 인증 관련 에러 코드
 */
enum class AuthErrorCode(
    val httpStatus: HttpStatus,
    val code: Int,
    private val errorMessageTemplate: String,
) {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, -10000, "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, -10001, "권한이 없습니다."),
    MISSING_JWT_PAYLOAD(HttpStatus.UNAUTHORIZED, -10002, "JWT 토큰에 %s가 존재하지 않습니다."),
    NOT_FOUND_MEMBER_BY_EMAIL(HttpStatus.NOT_FOUND, -10003, "해당 이메일의 유저가 존재하지 않습니다."),
    EMAIL_AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, -10004, "이메일 인증에 실패하였습니다."),
    INVALID_EMAIL_JWT(HttpStatus.UNAUTHORIZED, -10005, "유효하지 않은 이메일 JWT입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, -10006, "이미 존재하는 이메일입니다."),
    INVALID_EMAIL_PATTERN(HttpStatus.BAD_REQUEST, -10007, "유효하지 않은 이메일 형식입니다."),
    INVALID_NICKNAME_PATTERN(HttpStatus.BAD_REQUEST, -10008, "유효하지 않은 닉네임 형식입니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, -10009, "유효하지 않은 비밀번호 형식입니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, -10010, "로그인에 실패하였습니다."),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, -10011, "이메일 전송에 실패하였습니다."),
    INVALID_EMAIL_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, -10012, "유효하지 않은 이메일 인증 코드입니다."),
    OAUTH2_FETCH_FAILED(HttpStatus.UNAUTHORIZED, -10013, "[%s] OAuth2 정보를 가져오는데 실패하였습니다."),
    ALREADY_REGISTERED_ANOTHER_METHOD(HttpStatus.BAD_REQUEST, -10014, "이미 다른 방식으로 가입된 이메일입니다."),

    ;
    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}