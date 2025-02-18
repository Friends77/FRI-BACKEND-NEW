package com.example.friendsmessagecontroller.controller

import com.example.friendsmessagecontroller.service.MessageService
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class ChatWebSocketHandler(
    private val messageService: MessageService
) : TextWebSocketHandler(){
    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
    }
}