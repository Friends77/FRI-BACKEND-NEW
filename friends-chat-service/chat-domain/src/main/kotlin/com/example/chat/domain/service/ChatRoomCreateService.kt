package com.example.chat.domain.service

import com.example.chat.domain.entity.ChatRoom
import com.example.chat.domain.entity.ChatRoomMember
import com.example.chat.domain.exception.IllegalChatRoomPropertyException
import com.example.chat.domain.repository.ChatRoomRepository
import com.example.chat.domain.valueobject.type.CategorySubType
import com.example.chat.domain.valueobject.Profile
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChatRoomCreateService(
    private val chatRoomRepository: ChatRoomRepository
) {
    fun createChatRoom(
        title: String,
        imageUrl: String?,
        categoryList: List<String>,
        managerId: UUID,
        profile: Profile
    ) {
        if (categoryList.isEmpty()) throw IllegalChatRoomPropertyException("최소 1개 이상의 카테고리가 필요합니다.")
        if (categoryList.size > 5) throw IllegalChatRoomPropertyException("최대 5개까지의 카테고리만 가능합니다.")
        if (title.isBlank()) throw IllegalChatRoomPropertyException("채팅방 제목을 입력해주세요.")
        if (title.length > 50) throw IllegalChatRoomPropertyException("채팅방 제목은 50자 이하로 입력해주세요.")

        val categories = categoryList.map {
            try {
                CategorySubType.valueOf(it)
            } catch (e: IllegalArgumentException) {
                throw IllegalChatRoomPropertyException("$it 은 존재하지 않는 카테고리입니다.")
            }
        }

        val chatRoom = ChatRoom(managerId, title, categories)
        chatRoom.changeProfileImageUrl(imageUrl)
        chatRoom.addChatRoomMember(ChatRoomMember(chatRoom, managerId, profile))
        chatRoomRepository.save(chatRoom)
    }
}