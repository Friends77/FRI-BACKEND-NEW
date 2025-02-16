package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.valueobject.Birth
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
    var birth: Birth? = null
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
    var profileImageUrl: String? = null
        protected set

    @OneToMany(mappedBy = "profile", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableProfileCategories: MutableList<ProfileCategory> = mutableListOf()
    val profileCategories: List<ProfileCategory> get() = mutableProfileCategories.toList()

    // 한번 변경하면 다시 null 로 변경할 수 없도록 하기 위해 nullable 로 설정하지 않음
    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changeBirth(birth: Birth) {
        this.birth = birth
    }

    fun changeGender(gender : Gender) {
        this.gender = gender
    }

    fun changeLocation(location : Location) {
        this.location = location
    }

    fun changeMbti(mbti : MBTI) {
        this.mbti = mbti
    }

    fun changeSelfDescription(selfDescription : String) {
        this.selfDescription = selfDescription
    }

    // nullable 로 설정하여 기본 이미지 (null) 로 변경 가능
    fun changeImageUrl(imageUrl : String?) {
        this.profileImageUrl = imageUrl
    }

    fun changeCategories(categories : List<Category>) {
        mutableProfileCategories.clear()
        categories.forEach { addProfileCategory(it) }
    }

    private fun addProfileCategory(category: Category) {
        mutableProfileCategories.add(ProfileCategory(profile = this, category = category))
    }
}