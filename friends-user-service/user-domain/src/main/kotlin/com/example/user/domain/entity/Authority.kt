package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseTimeEntity
import com.example.user.domain.valueobject.AuthorityRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Authority(
    role: AuthorityRole,
    member: Member
) : BaseTimeEntity() {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: AuthorityRole = role

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member = member
}