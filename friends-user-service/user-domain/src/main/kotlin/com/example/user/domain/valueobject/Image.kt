package com.example.user.domain.valueobject

class Image(
    url: String
) {
    val url: String = url

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        return url == other.url
    }

    override fun hashCode(): Int {
        return url.hashCode()
    }
}