package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalProfileArgumentException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class SelfDescription(
    value : String
) {
    @Column(name = "self_description", nullable = true)
    val value: String = value

    init {
        if (value.length > 100) {
            throw IllegalProfileArgumentException("자기소개는 100자 이하여야 합니다.")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SelfDescription

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}