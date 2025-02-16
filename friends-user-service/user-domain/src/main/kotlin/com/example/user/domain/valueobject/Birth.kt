package com.example.user.domain.valueobject

import com.example.user.domain.exception.InvalidProfilePropertyException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
class Birth private constructor(
    @Column(name = "birth")
    val localDate: LocalDate
) {
    companion object {
        fun of(localDate: LocalDate): Birth {
            if (localDate.isAfter(LocalDate.now())) {
                throw InvalidProfilePropertyException("생년월일은 현재 날짜 이전이어야 합니다.")
            }
            if (localDate.isBefore(LocalDate.of(1900, 1, 1))) {
                throw InvalidProfilePropertyException("생년월일은 1900년 1월 1일 이후여야 합니다.")
            }
            return Birth(localDate)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Birth

        return localDate == other.localDate
    }

    override fun hashCode(): Int {
        return localDate.hashCode()
    }
}