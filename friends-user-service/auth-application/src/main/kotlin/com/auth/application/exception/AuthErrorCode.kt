package com.auth.application.exception

import org.springframework.http.HttpStatus

enum class AuthErrorCode(
    val httpStatus: HttpStatus,
    val code: Int,
    private val errorMessageTemplate: String,
) {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, -11003, "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, -11004, "권한이 없습니다."),
    MISSING_JWT_PAYLOAD(HttpStatus.UNAUTHORIZED, -11001, "JWT 토큰에 %s가 존재하지 않습니다."),

    ;
    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}