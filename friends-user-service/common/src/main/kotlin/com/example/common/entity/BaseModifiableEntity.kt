package com.example.common.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseModifiableEntity : BaseTimeEntity() {
    @LastModifiedDate
    @Column(nullable = false)
    lateinit var updatedAt: LocalDateTime
        protected set
}
