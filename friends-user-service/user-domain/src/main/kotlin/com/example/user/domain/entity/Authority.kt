package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseTimeEntity
import com.example.user.domain.valueobject.type.AuthorityRoleType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Authority(
    role: AuthorityRoleType,
    member: Member
) : BaseTimeEntity() {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val role: AuthorityRoleType = role

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member = member
}