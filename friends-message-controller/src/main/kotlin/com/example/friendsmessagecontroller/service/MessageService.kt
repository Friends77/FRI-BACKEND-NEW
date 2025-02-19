package com.example.friendsmessagecontroller.service

import com.example.friendsmessagecontroller.client.ChatServiceClient
import com.example.friendsmessagecontroller.event.KafkaEventPublisher
import com.example.friendsmessagecontroller.event.MessageReceiveEvent
import com.example.friendsmessagecontroller.event.MessageSendEvent
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap

@Service
class MessageService(
    private val chatServiceClient: ChatServiceClient,
    private val kafkaEventPublisher: KafkaEventPublisher,
    private val objectMapper: ObjectMapper
) {
    val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * key = chatRoomId, value = memberIds
     * 채팅방에 온라인으로 참여하고 있는 유저들의 아이디
     */
    private val chatOnlineUserIds = ConcurrentHashMap<String, MutableSet<String>>()

    /**
     * key = memberId, value = WebSocketSessions
     * 하나의 유저가 여러개의 세션을 가질 수 있기 때문에 MutableSet을 사용합니다. (ex. 웹, 모바일 에서 동시 접속)
     */
    private val userWebSocketSessions = ConcurrentHashMap<String, MutableSet<WebSocketSession>>()

    /**
     * 유저가 채팅에 접속하면 참여하고 있는 채팅방에 온라인 유저로 등록
     */
    fun connectChat(memberId : String, session: WebSocketSession) {
        val chatRoomIds = getJoinedChatRoomIds(memberId)

        userWebSocketSessions.computeIfAbsent(memberId) { ConcurrentHashMap.newKeySet() }.add(session)

        chatRoomIds.forEach { chatRoomId ->
            logger.debug("$memberId 가 $chatRoomId 에 온라인으로 접속되었습니다.")
            chatOnlineUserIds.computeIfAbsent(chatRoomId) { ConcurrentHashMap.newKeySet() }.add(memberId)
        }
    }

    fun disconnectChat(memberId : String, session: WebSocketSession) {
        userWebSocketSessions[memberId]?.removeIf { it.id == session.id }
        val chatRoomIds = getJoinedChatRoomIds(memberId)

        chatRoomIds.forEach { chatRoomId ->
            logger.debug("$memberId 가 $chatRoomId 에서 온라인 접속을 종료하였습니다.")
            chatOnlineUserIds[chatRoomId]?.remove(memberId)
        }
    }

    private fun getJoinedChatRoomIds(memberId : String) : List<String> {
        return chatServiceClient.getJoinedChatRoomIds(memberId)
    }

    // 메세지를 받으면 메세지 저장 서비스에서 메세지를 저장하고, kafka를 통해 메세지를 재전송합니다.
    fun receiveMessage(messageReceiveEvent: MessageReceiveEvent) {
        kafkaEventPublisher.publishMessageReceiveEvent(messageReceiveEvent)
    }

    fun sendMessage(messageSendEvent: MessageSendEvent) {
        val chatRoomId = messageSendEvent.chatRoomId
        val onlineUserIds = chatOnlineUserIds[chatRoomId]
        onlineUserIds?.forEach {
            val sessions = userWebSocketSessions[it] ?: return@forEach
            sessions.forEach { session ->
                logger.debug("$chatRoomId 에 메세지가 전송되었습니다 : $messageSendEvent")
                session.sendMessage(TextMessage(objectMapper.writeValueAsString(messageSendEvent)))
            }
        }
    }
}