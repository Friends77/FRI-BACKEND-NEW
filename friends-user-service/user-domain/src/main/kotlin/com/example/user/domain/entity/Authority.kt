package com.example.user.domain.entity

import com.example.user.domain.common.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Authority(
    role: Role,
    member: Member
) : BaseTimeEntity() {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: Role = role

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member = member

    enum class Role {
        ROLE_USER,
        ROLE_ADMIN,
    }
}