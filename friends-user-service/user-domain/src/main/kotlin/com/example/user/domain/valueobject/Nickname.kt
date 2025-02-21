package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalProfileArgumentException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Nickname(
    value : String
) {
    @Column(name = "nickname", nullable = false)
    val value: String = value

    init {
        if (!isValid(value)) {
            throw IllegalProfileArgumentException("유효하지 않는 닉네임 형식입니다 : $value")
        }
    }


    private fun isValid(nickname: String): Boolean {
        return nickname.matches(Regex("^[a-zA-Z0-9ㄱ-ㅎ가-힣]{2,10}$"))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Nickname

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}