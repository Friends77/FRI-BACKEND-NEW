package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalProfileArgumentException
import com.example.user.domain.valueobject.type.GenderType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class Gender(
    rawType: String
) {
    @Column(name = "gender", nullable = true)
    @Enumerated(EnumType.STRING)
    val type: GenderType = try {
        GenderType.valueOf(rawType)
    } catch (e: IllegalArgumentException) {
        throw IllegalProfileArgumentException("유효하지 않은 성별 값: $rawType \n 가능한 성별 값: ${GenderType.entries.joinToString(", ")}")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Gender

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }


}