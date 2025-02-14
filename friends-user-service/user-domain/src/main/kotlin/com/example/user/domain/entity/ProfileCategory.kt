package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseTimeEntity
import com.example.user.domain.valueobject.Category
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class ProfileCategory(
    profile: Profile,
    category: Category
) : BaseTimeEntity() {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    val profile: Profile = profile

    @Enumerated(EnumType.STRING)
    val category: Category = category
}