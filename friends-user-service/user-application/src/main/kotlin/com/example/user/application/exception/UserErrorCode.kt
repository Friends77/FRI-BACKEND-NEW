package com.example.user.application.exception

import org.springframework.http.HttpStatus

enum class UserErrorCode(
    val httpStatus: HttpStatus,
    val code: Int,
    private val errorMessageTemplate: String,
) {
    NOT_FOUND_MEMBER_BY_EMAIL(HttpStatus.NOT_FOUND, -10001, "해당 이메일의 유저가 존재하지 않습니다.")
    ;
    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}