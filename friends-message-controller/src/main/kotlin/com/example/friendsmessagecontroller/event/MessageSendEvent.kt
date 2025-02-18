package com.example.friendsmessagecontroller.event

import com.example.friendsmessagecontroller.valueobject.MessageType
import java.util.UUID

data class MessageSendEvent(
    val clientMessageId: String,
    val chatRoomId: UUID,
    val content: String,
    val type: MessageType
)