package com.example.chat.domain.valueobject

import com.example.chat.domain.exception.IllegalChatRoomPropertyException

class Title(
    value : String
) {
    val value: String = value

    init {
        if (value.isBlank()) throw IllegalChatRoomPropertyException("채팅방 제목은 공백일 수 없습니다.")
        if (value.length > 50) throw IllegalChatRoomPropertyException("채팅방 제목은 50자 이하여야 합니다.")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Title

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}