package com.example.chat.domain.entity

import com.example.chat.domain.entity.base.BaseTimeEntity
import com.example.chat.domain.valueobject.Category
import com.example.chat.domain.valueobject.converter.CategoryConverter
import com.example.chat.domain.valueobject.type.CategorySubType
import jakarta.persistence.Column
import jakarta.persistence.Convert
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

    @Column(name = "category", nullable = false)
    @Convert(converter = CategoryConverter::class)
    val category: Category = category
}