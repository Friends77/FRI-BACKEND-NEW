package com.example.message.service.event

import com.example.message.domain.event.EventHandler
import com.example.message.domain.event.EventPublisher
import com.example.message.domain.event.MessageReceiveEvent
import com.example.message.domain.event.MessageSendEvent
import com.example.message.domain.service.MessageSaveService
import com.example.message.domain.valueobject.MessageType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class MessageSaveEventHandler(
    private val messageSaveService: MessageSaveService,
    private val eventPublisher: EventPublisher
) : EventHandler {
    override fun messageReceiveEventHandler(event: MessageReceiveEvent) {
        val message = messageSaveService.saveMessage(
            chatRoomId = UUID.fromString(event.chatRoomId),
            senderId = UUID.fromString(event.senderId),
            content = event.content,
            type = MessageType.valueOf(event.type)
        )

        val messageSendEvent = MessageSendEvent(
            clientMessageId = event.clientMessageId,
            messageId = message.id.toString(),
            chatRoomId = message.chatRoomId.toString(),
            senderId = message.senderId.toString(),
            content = message.content,
            createdAt = message.createdAt.toString(),
            type = message.type.toString()
        )

        eventPublisher.publishMessageSendEvent(messageSendEvent)
    }
}