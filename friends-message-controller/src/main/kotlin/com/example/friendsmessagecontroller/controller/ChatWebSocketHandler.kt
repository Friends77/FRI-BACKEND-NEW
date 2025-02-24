package com.example.friendsmessagecontroller.controller

import com.example.friendsmessagecontroller.controller.dto.MessageReceiveDto
import com.example.friendsmessagecontroller.event.KafkaEventPublisher
import com.example.friendsmessagecontroller.event.MessageReceiveEvent
import com.example.friendsmessagecontroller.service.ChatConnectService
import com.example.friendsmessagecontroller.service.MessageService
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class ChatWebSocketHandler(
    private val kafkaEventPublisher: KafkaEventPublisher,
    private val chatConnectService: ChatConnectService,
    private val objectMapper: ObjectMapper
) : TextWebSocketHandler(){
    private val logger = LoggerFactory.getLogger(this::class.java)

    companion object {
        private const val SEND_TIME_LIMIT = 2000 // 2초
        private const val BUFFER_SIZE_LIMIT = 1024 * 1024 // 1MB
        private const val TEXT_MAX_SIZE = 1 * 1024 * 1024 // 1MB - kafka message max size
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val memberId = getMemberId(session)
        session.textMessageSizeLimit = TEXT_MAX_SIZE
        chatConnectService.connectChat(memberId, ConcurrentWebSocketSessionDecorator(session, SEND_TIME_LIMIT, BUFFER_SIZE_LIMIT))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val messageReceiveDto = try {
            objectMapper.readValue(message.payload, MessageReceiveDto::class.java)
        } catch (e: Exception) {
            logger.error("메시지 파싱 에러", e)
            session.sendMessage(TextMessage("메시지 파싱 에러 : ${e.message}"))
            return
        }
        val messageReceiveEvent = MessageReceiveEvent(
            clientMessageId = messageReceiveDto.clientMessageId,
            chatRoomId = messageReceiveDto.chatRoomId,
            senderId = getMemberId(session),
            content = messageReceiveDto.content,
            type = messageReceiveDto.type
        )
        kafkaEventPublisher.publishMessageReceiveEvent(messageReceiveEvent)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val memberId = getMemberId(session)
        chatConnectService.disconnectChat(memberId, session)
    }

    private fun getMemberId(session: WebSocketSession): String {
        // TODO : MEMBER_ID 파싱 에러 처리
        return session.attributes["MEMBER_ID"] as String
    }
}