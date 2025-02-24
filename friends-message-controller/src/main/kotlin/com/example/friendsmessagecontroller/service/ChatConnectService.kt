package com.example.friendsmessagecontroller.service

import com.example.friendsmessagecontroller.client.ChatServiceClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.WebSocketSession
import java.util.concurrent.ConcurrentHashMap

@Service
class ChatConnectService(
    private val chatServiceClient: ChatServiceClient,
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

    fun getOnlineUserSessions(chatRoomId : String) : List<WebSocketSession> {
        val memberIds = chatOnlineUserIds[chatRoomId] ?: return emptyList()
        return memberIds.flatMap { userWebSocketSessions[it] ?: emptySet() }.toList()
    }

    fun connectChat(memberId : String, session: WebSocketSession) {
        val chatRoomIds = getJoinedChatRoomIds(memberId)

        userWebSocketSessions.computeIfAbsent(memberId) { ConcurrentHashMap.newKeySet() }.add(session)

        chatRoomIds.forEach { chatRoomId ->
            logger.info("$memberId 가 $chatRoomId 에 온라인으로 접속되었습니다.")
            chatOnlineUserIds.computeIfAbsent(chatRoomId) { ConcurrentHashMap.newKeySet() }.add(memberId)
        }
    }

    fun disconnectChat(memberId : String, session: WebSocketSession) {
        userWebSocketSessions[memberId]?.removeIf { it.id == session.id }
        val chatRoomIds = getJoinedChatRoomIds(memberId)

        chatRoomIds.forEach { chatRoomId ->
            logger.info("$memberId 가 $chatRoomId 에서 온라인 접속을 종료하였습니다.")
            chatOnlineUserIds[chatRoomId]?.remove(memberId)
        }
    }

    private fun getJoinedChatRoomIds(memberId : String) : List<String> {
        val chatRoomIds = chatServiceClient.getJoinedChatRoomIds(memberId)
        logger.info("$memberId 가 참여하고 있는 채팅방 목록 : $chatRoomIds")
        return chatRoomIds
    }
}