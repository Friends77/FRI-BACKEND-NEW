package com.example.message.infrastructure.event

import com.example.message.domain.event.EventHandler
import com.example.message.domain.event.EventListener
import com.example.message.domain.event.MessageReceiveEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaEventListener(
    private val objectMapper: ObjectMapper,
    private val eventHandler: EventHandler
) : EventListener {
    val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = ["chat-message-receive"], groupId = "message-service")
    override fun consumeMessageReceiveEvent(payload: String) {
        val event = objectMapper.readValue(payload, MessageReceiveEvent::class.java)
        logger.info("MessageReceiveEvent 수신 -> $event")
        eventHandler.messageReceiveEventHandler(event)
    }
}