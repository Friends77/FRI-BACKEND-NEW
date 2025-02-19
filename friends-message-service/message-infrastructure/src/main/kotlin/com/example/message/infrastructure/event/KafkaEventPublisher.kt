package com.example.message.infrastructure.event

import com.example.message.domain.event.EventPublisher
import com.example.message.domain.event.MessageSendEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventPublisher(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : EventPublisher {
    override fun publishMessageSendEvent(event: MessageSendEvent) {
        val key = event.senderId
        val topic = "chat-message-send"
        val value = objectMapper.writeValueAsString(event)
        kafkaTemplate.send(topic, key, value)
    }
}