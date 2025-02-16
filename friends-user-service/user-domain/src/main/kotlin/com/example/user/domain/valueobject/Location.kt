package com.example.user.domain.valueobject

import com.example.user.domain.exception.InvalidProfilePropertyException
import jakarta.persistence.Embeddable

@Embeddable
class Location private constructor(
    val latitude: Double,
    val longitude: Double,
) {
    companion object {
        fun of(latitude: Double, longitude: Double): Location {
            if (latitude < -90 || latitude > 90) {
                throw InvalidProfilePropertyException("위도는 -90에서 90 사이의 값이어야 합니다.")
            }
            if (longitude < -180 || longitude > 180) {
                throw InvalidProfilePropertyException("경도는 -180에서 180 사이의 값이어야 합니다.")
            }
            return Location(latitude, longitude)
        }
    }

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