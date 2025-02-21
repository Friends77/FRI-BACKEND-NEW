package com.example.user.domain.valueobject

import com.example.user.domain.exception.IllegalProfileArgumentException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
class Birth (
    @Column(name = "birth", nullable = true)
    val localDate: LocalDate
) {
    init {
        if (localDate.isAfter(LocalDate.now())) {
            throw IllegalProfileArgumentException("생년월일은 현재 날짜 이전이어야 합니다.")
        }
        if (localDate.isBefore(LocalDate.of(1900, 1, 1))) {
            throw IllegalProfileArgumentException("생년월일은 1900년 1월 1일 이후여야 합니다.")
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