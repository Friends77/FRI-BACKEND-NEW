package com.example.chat.domain.exception

class InvalidChatRoomPropertyException(message: String) : BaseException(ErrorCode.INVALID_CHAT_ROOM_PROPERTY, message)