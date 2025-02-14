package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.valueobject.Category
import com.example.user.domain.valueobject.Gender
import com.example.user.domain.valueobject.Location
import com.example.user.domain.valueobject.MBTI
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.time.LocalDate

@Entity
class Profile(
    member: Member,
    nickname: String,
) : BaseModifiableEntity() {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member

    @Column(nullable = false)
    var nickname: String = nickname
        protected set

    @Column(nullable = true)
    var birth: LocalDate? = null
        protected set

    @Column(nullable = true)
    var gender : Gender? = null
        protected set

    @Column(nullable = true)
    var location: Location? = null
        protected set

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var mbti : MBTI? = null
        protected set

    @Column(nullable = true)
    var selfDescription: String? = null
        protected set

    @Column(nullable = true)
    var imageUrl: String? = null
        protected set

    @OneToMany(mappedBy = "profile", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableProfileCategories: MutableList<ProfileCategory> = mutableListOf()
    val profileCategories: List<ProfileCategory> get() = mutableProfileCategories.toList()

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun updateProfile(
        birth: LocalDate?,
        gender: Gender?,
        location: Location?,
        mbti: MBTI?,
        selfDescription: String?,
        imageUrl: String?,
        categories: List<Category>,
    ) {
        this.birth = birth
        this.gender = gender
        this.location = location
        this.mbti = mbti
        this.selfDescription = selfDescription
        this.imageUrl = imageUrl
        mutableProfileCategories.clear()
        categories.forEach { addProfileCategory(it) }
    }

    private fun addProfileCategory(category: Category) {
        mutableProfileCategories.add(ProfileCategory(profile = this, category = category))
    }
}