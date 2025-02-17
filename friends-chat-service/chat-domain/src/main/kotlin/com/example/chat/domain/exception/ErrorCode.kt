package com.example.chat.domain.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    private val errorMessageTemplate: String,
) {
    INVALID_CHAT_ROOM_PROPERTY(HttpStatus.BAD_REQUEST, "%s"),

    ;
    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}