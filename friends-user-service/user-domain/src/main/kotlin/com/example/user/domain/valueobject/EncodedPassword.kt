package com.example.user.domain.valueobject


class EncodedPassword(
    value : String
){
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