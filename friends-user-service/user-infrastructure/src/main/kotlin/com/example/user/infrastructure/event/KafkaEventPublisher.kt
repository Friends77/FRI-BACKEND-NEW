package com.example.user.infrastructure.event

import com.example.user.domain.event.EventPublisher
import com.example.user.domain.event.ProfileImageUrlChangedEvent
import com.example.user.domain.event.ProfileNicknameChangedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventPublisher(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : EventPublisher{
    override fun publishProfileNicknameChangedEvent(event: ProfileNicknameChangedEvent) {
        val key = event.memberId.toString()
        val topic = "profile-nickname-changed"
        val value = objectMapper.writeValueAsString(event)
        kafkaTemplate.send(topic, key, value)
    }

    override fun publishProfileImageUrlChangedEvent(event: ProfileImageUrlChangedEvent) {
        val key = event.memberId.toString()
        val topic = "profile-image-url-changed"
        val value = objectMapper.writeValueAsString(event)
        kafkaTemplate.send(topic, key, value)
    }
}