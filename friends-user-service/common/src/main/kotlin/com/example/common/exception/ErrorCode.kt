package com.example.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val code: Int,
    private val errorMessageTemplate: String,
) {
    // 서버 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -1, "서버 내부 오류입니다."),

    // Member Error 10000번대
    NOT_FOUND_MEMBER_BY_EMAIL(HttpStatus.NOT_FOUND, -10001, "해당 이메일의 유저가 존재하지 않습니다."),

    // Auth Error 11000번대
    MISSING_JWT_PAYLOAD(HttpStatus.UNAUTHORIZED, -11001, "JWT 토큰에 %s가 존재하지 않습니다."),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, -11002, "유효하지 않은 JWT 토큰입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, -11003, "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, -11004, "권한이 없습니다."),
    INVALID_NICKNAME(HttpStatus.BAD_REQUEST, -11005, "유효하지 않은 닉네임입니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, -11006, "유효하지 않은 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, -11007, "유효하지 않은 비밀번호입니다."),
    EMAIL_AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, -11008, "이메일 인증에 실패했습니다."),
    INVALID_TOKEN_TYPE(HttpStatus.BAD_REQUEST, -11009, "유효하지 않은 토큰 타입입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, -11010, "이미 존재하는 이메일입니다."),
    ;

    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}
