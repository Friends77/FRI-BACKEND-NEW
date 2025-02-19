package com.example.message.domain.entity

import com.example.message.domain.entity.base.BaseModifiableEntity
import com.example.message.domain.valueobject.MessageType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.UUID

@Entity
class Message(
    chatRoomId : UUID,
    senderId : UUID,
    content : String,
    type : MessageType
) : BaseModifiableEntity() {
    @Column(nullable = false)
    val chatRoomId : UUID = chatRoomId

    @Column(nullable = false)
    val senderId : UUID = senderId

    @Column(nullable = false, columnDefinition = "TEXT")
    var content : String = content
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type : MessageType = type
        protected set

    fun changeContent(content: String) {
        this.content = content
    }

    fun delete() {
        this.content = "삭제된 메시지입니다."
        this.type = MessageType.DELETED
    }

    override fun toString(): String {
        return "Message(chatRoomId=$chatRoomId, senderId=$senderId, content='$content', type=$type)"
    }


}

