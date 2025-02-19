package com.example.friendsmessagecontroller.event

import com.example.friendsmessagecontroller.service.MessageService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaEventListener(
    private val objectMapper: ObjectMapper,
    private val messageService: MessageService
) {
    val logger = LoggerFactory.getLogger(this::class.java)
    /**
     * kafka 설정 필수 값 "auto.offset.reset = latest"
     * message-controller 인스턴스마다 다른 groupId를 사용해야 합니다
     */
    @KafkaListener(topics = ["chat-message-send"], groupId = "message-controller-0")
    fun consumeMessageSendEvent(payload: String) {
        val event = objectMapper.readValue(payload, MessageSendEvent::class.java)
        logger.info("MessageSendEvent 수신 -> $event")
        messageService.sendMessage(event)
    }
}