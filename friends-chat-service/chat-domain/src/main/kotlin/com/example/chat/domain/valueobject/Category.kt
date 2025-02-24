package com.example.chat.domain.valueobject

import com.example.chat.domain.exception.IllegalChatRoomPropertyException
import com.example.chat.domain.valueobject.type.CategorySubType

class Category (
    rawType: String
){
    val type: CategorySubType = try {
        CategorySubType.valueOf(rawType)
    } catch (e: IllegalArgumentException) {
        throw IllegalChatRoomPropertyException("유효하지 않은 카테고리 값: $rawType")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }

}