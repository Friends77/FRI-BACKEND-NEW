package com.example.chat.infrastructure.event

import com.example.chat.domain.event.EventConsumer
import com.example.chat.domain.event.EventHandler
import com.example.chat.domain.event.ProfileImageUrlChangedEvent
import com.example.chat.domain.event.ProfileNicknameChangedEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaEventConsumer (
    private val eventHandler: EventHandler,
    private val objectMapper: ObjectMapper
) : EventConsumer {
    val logger = LoggerFactory.getLogger(this::class.java)


    @KafkaListener(topics = ["profile-nickname-changed"], groupId = "chat-service")
    override fun consumeProfileNicknameChangedEvent(payload: String) {
        logger.info("consumeProfileNicknameChangedEvent 수신 -> $payload")
        val event = try {
            objectMapper.readValue(payload, ProfileNicknameChangedEvent::class.java)
        } catch (e: Exception) {
            throw IllegalArgumentException("ProfileNicknameChangedEvent 파싱 에러", e)
        }
        eventHandler.handleProfileNicknameChangedEvent(event)
    }

    @KafkaListener(topics = ["profile-image-url-changed"], groupId = "chat-service")
    override fun consumeProfileImageUrlChangedEvent(payload: String) {
        logger.info("consumeProfileImageUrlChangedEvent 수신 -> $payload")
        val event = try {
            objectMapper.readValue(payload, ProfileImageUrlChangedEvent::class.java)
        } catch (e: Exception) {
            throw IllegalArgumentException("ProfileImageUrlChangedEvent 파싱 에러", e)
        }
        eventHandler.handleProfileImageUrlChangedEvent(event)
    }
}