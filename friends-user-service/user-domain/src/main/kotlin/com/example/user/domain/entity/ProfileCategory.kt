package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseTimeEntity
import com.example.user.domain.valueobject.Category
import com.example.user.domain.valueobject.attributeconverter.CategoryConverter
import com.example.user.domain.valueobject.type.CategorySubType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embedded
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

    @Column(name = "category", nullable = false)
    @Convert(converter = CategoryConverter::class)
    val category: Category = category
}