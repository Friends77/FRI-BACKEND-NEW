package com.example.user.domain.valueobject

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Image(
    url: String
) {
    @Column(name = "image_url", nullable = true)
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