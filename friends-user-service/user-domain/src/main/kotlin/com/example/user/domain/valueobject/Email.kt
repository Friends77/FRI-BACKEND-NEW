package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalMemberArgumentException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Email(
    value: String
) {
    @Column(name = "email", nullable = false, unique = true)
    val value: String = value

    init {
        if (!isValid(value)) {
            throw IllegalMemberArgumentException("유효하지 않는 이메일 형식입니다 : $value")
        }
    }

    private fun isValid(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$")
        return emailRegex.matches(email)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Email

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}