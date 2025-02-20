package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalProfileArgumentException
import com.example.user.domain.valueobject.type.MBTIType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class MBTI(
    rawType: String
) {
    @Column(name = "mbti", nullable = true)
    val type: MBTIType = try {
        MBTIType.valueOf(rawType)
    } catch (e: IllegalArgumentException) {
        throw IllegalProfileArgumentException("유효하지 않은 MBTI 값: $rawType")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MBTI

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }


}