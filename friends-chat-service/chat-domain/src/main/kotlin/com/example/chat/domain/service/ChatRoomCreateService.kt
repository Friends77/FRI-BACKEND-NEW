package com.example.chat.domain.service

import com.example.chat.domain.entity.ChatRoom
import com.example.chat.domain.exception.InvalidChatRoomPropertyException
import com.example.chat.domain.repository.ChatRoomRepository
import com.example.chat.domain.valueobject.Category
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatRoomCreateService(
    private val chatRoomRepository: ChatRoomRepository
) {
    fun createChatRoom(
        title: String,
        managerId: UUID,
        categories: List<Category>
    ) {
        if (categories.isEmpty()) throw InvalidChatRoomPropertyException("최소 1개 이상의 카테고리가 필요합니다.")
        if (categories.size > 5) throw InvalidChatRoomPropertyException("최대 5개까지의 카테고리만 가능합니다.")
        if (title.isBlank()) throw InvalidChatRoomPropertyException("채팅방 제목을 입력해주세요.")
        if (title.length > 50) throw InvalidChatRoomPropertyException("채팅방 제목은 50자 이하로 입력해주세요.")
        val chatRoom = ChatRoom(managerId, title, categories)
        chatRoomRepository.save(chatRoom)
    }
}