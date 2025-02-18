package com.example.friendsmessagecontroller.event

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventPublisher(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    fun publishMessageSendEvent(event: MessageSendEvent) {
        val key = event.chatRoomId.toString()
        val topic = "message-send"
        val value = objectMapper.writeValueAsString(event)
        kafkaTemplate.send(topic, key, value)
    }
}