package com.example.chat.domain.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val defaultMessage: String,
) {
    INVALID_CHAT_ROOM_PROPERTY(HttpStatus.BAD_REQUEST, "채팅방 속성이 잘못되었습니다."),
    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방을 찾을 수 없습니다."),

    ;
}