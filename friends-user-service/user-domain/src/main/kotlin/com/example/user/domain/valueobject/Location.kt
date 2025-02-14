package com.example.user.domain.valueobject

import jakarta.persistence.Embeddable

@Embeddable
class Location(
    val latitude: Double,
    val longitude: Double,
) {
    fun distanceTo(other: Location): Double {
        val theta = longitude - other.longitude
        var dist = Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(other.latitude)) +
                Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(other.latitude)) *
                Math.cos(Math.toRadians(theta))
        dist = Math.acos(dist)
        dist = Math.toDegrees(dist)
        dist *= 60 * 1.1515
        return dist
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }
}