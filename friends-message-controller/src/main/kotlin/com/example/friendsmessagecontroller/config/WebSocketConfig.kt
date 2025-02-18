package com.example.friendsmessagecontroller.config

import com.example.friendsmessagecontroller.controller.ChatWebSocketHandler
import com.example.friendsmessagecontroller.controller.ChatWebSocketInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val chatWebSocketHandler: ChatWebSocketHandler,
    private val chatWebSocketInterceptor: ChatWebSocketInterceptor
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
            .addHandler(chatWebSocketHandler, "/ws/chat")
            .addInterceptors(chatWebSocketInterceptor)
            .setAllowedOrigins("*")
    }
}