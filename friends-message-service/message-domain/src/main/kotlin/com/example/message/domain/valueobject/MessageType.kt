package com.example.message.domain.valueobject

enum class MessageType {
    TEXT,
    IMAGE,
    DELETED,

    SYSTEM,
    SYSTEM_MEMBER_ENTER, // 채팅방 멤버 입장
    SYSTEM_MEMBER_LEAVE, // 채팅방 멤버 퇴장
    SYSTEM_NEW_MANAGER,
    ;

    companion object {
        fun getNonSystemTypes(): List<MessageType> = listOf(TEXT, IMAGE, DELETED)

        fun getSystemTypes(): List<MessageType> = listOf(SYSTEM, SYSTEM_MEMBER_ENTER, SYSTEM_MEMBER_LEAVE, SYSTEM_NEW_MANAGER)
    }
}