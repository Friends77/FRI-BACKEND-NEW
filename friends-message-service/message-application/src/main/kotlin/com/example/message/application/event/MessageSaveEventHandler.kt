package com.example.message.application.event

import com.example.message.domain.event.EventHandler
import com.example.message.domain.event.EventPublisher
import com.example.message.domain.event.MessageReceiveEvent
import com.example.message.domain.event.MessageSendEvent
import com.example.message.application.service.MessageSaveService
import com.example.message.domain.valueobject.MessageType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class MessageSaveEventHandler(
    private val messageSaveService: MessageSaveService,
    private val eventPublisher: EventPublisher
) : EventHandler {
    val logger = LoggerFactory.getLogger(this::class.java)

    override fun messageReceiveEventHandler(event: MessageReceiveEvent) {
        logger.info("MessageReceiveEventHandler 호출 -> $event")

        val chatRoomId = UUID.fromString(event.chatRoomId)
        val senderId = UUID.fromString(event.senderId)
        val content = event.content
        val type = MessageType.valueOf(event.type)

        val message = messageSaveService.saveMessage(
            chatRoomId = chatRoomId,
            senderId = senderId,
            content = content,
            type = type
        )

        logger.info("message 저장 완료 -> $message")

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