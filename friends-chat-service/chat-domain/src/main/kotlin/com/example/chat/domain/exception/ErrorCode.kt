package com.example.chat.domain.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    private val errorMessageTemplate: String,
) {
    INVALID_CHAT_ROOM_PROPERTY(HttpStatus.BAD_REQUEST, "%s"),
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방을 찾을 수 없습니다."),

    ;
    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}