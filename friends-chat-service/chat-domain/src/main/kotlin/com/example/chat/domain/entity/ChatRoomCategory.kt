package com.example.chat.domain.entity

import com.example.chat.domain.entity.base.BaseTimeEntity
import com.example.chat.domain.valueobject.Category
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class ChatRoomCategory(
    chatRoom: ChatRoom,
    category: Category
) : BaseTimeEntity(){
    @ManyToOne(fetch = FetchType.LAZY)
    val chatRoom: ChatRoom = chatRoom

    @Enumerated(EnumType.STRING)
    val category: Category = category
}