package com.example.friendsmessagecontroller.service

import com.example.friendsmessagecontroller.event.MessageSendEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.socket.TextMessage

@Service
@Transactional
class MessageService(
    private val chatConnectService: ChatConnectService,
    private val objectMapper: ObjectMapper
) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun sendMessage(messageSendEvent: MessageSendEvent) {
        val chatRoomId = messageSendEvent.chatRoomId
        val sessions = chatConnectService.getOnlineUserSessions(chatRoomId)
        sessions.forEach { session ->
            try {
                session.sendMessage(TextMessage(objectMapper.writeValueAsString(messageSendEvent)))
            } catch (e: Exception) {
                logger.error("메시지 전송 에러", e)
            }
        }
    }
}