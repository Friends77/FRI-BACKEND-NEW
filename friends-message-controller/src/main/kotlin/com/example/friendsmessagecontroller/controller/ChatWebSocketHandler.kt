package com.example.friendsmessagecontroller.controller

import com.example.friendsmessagecontroller.event.MessageSendEvent
import com.example.friendsmessagecontroller.service.MessageService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.UUID

@Component
class ChatWebSocketHandler(
    private val messageService: MessageService,
    private val objectMapper: ObjectMapper
) : TextWebSocketHandler(){
    companion object {
        private const val SEND_TIME_LIMIT = 2000 // 2초
        private const val BUFFER_SIZE_LIMIT = 1024 * 1024 // 1MB
        private const val TEXT_MAX_SIZE = 1 * 1024 * 1024 // 1MB - kafka message max size
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        val memberId = getMemberId(session)
        session.textMessageSizeLimit = TEXT_MAX_SIZE
        messageService.connectChat(memberId, ConcurrentWebSocketSessionDecorator(session, SEND_TIME_LIMIT, BUFFER_SIZE_LIMIT))
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val messageSendEvent = try {
            objectMapper.readValue(message.payload, MessageSendEvent::class.java)
        } catch (e: Exception) {
            session.sendMessage(TextMessage("메시지 파싱 에러"))
            return
        }

        messageService.sendMessage(messageSendEvent)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        val memberId = getMemberId(session)
        messageService.disconnectChat(memberId, session)
    }

    private fun getMemberId(session: WebSocketSession): UUID {
        // TODO : MEMBER_ID 파싱 에러 처리
        val memberId = session.attributes["MEMBER_ID"] as String
        return UUID.fromString(memberId)
    }
}