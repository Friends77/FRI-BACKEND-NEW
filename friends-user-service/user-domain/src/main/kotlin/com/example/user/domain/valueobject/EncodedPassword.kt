package com.example.user.domain.valueobject

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class EncodedPassword(
    value : String
){
    @Column(name = "password", nullable = true)
    val value : String = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncodedPassword

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}