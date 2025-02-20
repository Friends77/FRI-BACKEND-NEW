package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalProfileArgumentException
import com.example.user.domain.valueobject.type.CategorySubType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Category (
    rawType: String
){
    @Column(name = "category", nullable = true)
    val type: CategorySubType = try {
        CategorySubType.valueOf(rawType)
    } catch (e: IllegalArgumentException) {
        throw IllegalProfileArgumentException("유효하지 않은 카테고리 값: $rawType")
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