package com.example.message.application.service

import com.example.message.domain.entity.Message
import com.example.message.domain.repository.MessageRepository
import com.example.message.domain.valueobject.MessageType
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MessageSaveService(
    private val messageRepository: MessageRepository
) {
    fun saveMessage(
        chatRoomId: UUID,
        senderId: UUID,
        content: String,
        type: MessageType
    ) : Message{
        return messageRepository.save(Message(chatRoomId, senderId, content, type))
    }
}