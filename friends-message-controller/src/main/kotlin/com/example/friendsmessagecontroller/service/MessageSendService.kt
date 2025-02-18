package com.example.friendsmessagecontroller.service

import com.example.friendsmessagecontroller.client.ChatServiceClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.WebSocketSession
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Service
class MessageSendService(
    private val chatServiceClient: ChatServiceClient
) {
    val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * key = chatRoomId, value = memberIds
     * 채팅방에 온라인으로 참여하고 있는 유저들의 아이디
     */
    private val chatOnlineUserIds = ConcurrentHashMap<UUID, MutableSet<UUID>>()

    /**
     * key = memberId, value = WebSocketSessions
     * 하나의 유저가 여러개의 세션을 가질 수 있기 때문에 MutableSet을 사용합니다. (ex. 웹, 모바일 에서 동시 접속)
     */
    private val userWebSocketSessions = ConcurrentHashMap<UUID, MutableSet<WebSocketSession>>()

    /**
     * 유저가 채팅에 접속하면 참여하고 있는 채팅방에 온라인 유저로 등록
     */
    fun connectChat(memberId : UUID, session: WebSocketSession) {
        val chatRoomIds = getJoinedChatRoomIds(memberId)

        userWebSocketSessions.computeIfAbsent(memberId) { ConcurrentHashMap.newKeySet() }.add(session)

        chatRoomIds.forEach { chatRoomId ->
            chatOnlineUserIds.computeIfAbsent(chatRoomId) { ConcurrentHashMap.newKeySet() }.add(memberId)
        }
    }

    fun disconnectChat(memberId : UUID, session: WebSocketSession) {
        userWebSocketSessions[memberId]?.removeIf { it.id == session.id }
        val chatRoomIds = getJoinedChatRoomIds(memberId)

        chatRoomIds.forEach { chatRoomId ->
            chatOnlineUserIds[chatRoomId]?.remove(memberId)
        }
    }

    private fun getJoinedChatRoomIds(memberId : UUID) : List<UUID> {
        return chatServiceClient.getJoinedChatRoomIds(memberId).mapNotNull {
            try {
                UUID.fromString(it)
            } catch (e: IllegalArgumentException) {
                logger.error("온라인 채팅방 아이디 변환 실패 : id = $it")
                null
            }
        }
    }
}