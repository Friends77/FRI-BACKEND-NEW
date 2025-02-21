package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalMemberArgumentException
import com.example.user.domain.valueobject.type.OAuth2ProviderType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

class OAuth2Provider(
    rawType : String
) {
    val type : OAuth2ProviderType = try {
        OAuth2ProviderType.valueOf(rawType)
    } catch (e: IllegalArgumentException) {
        throw IllegalMemberArgumentException("$rawType 은 지원하지 않는 OAuth2ProviderType 입니다.")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OAuth2Provider

        return type == other.type
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }


}