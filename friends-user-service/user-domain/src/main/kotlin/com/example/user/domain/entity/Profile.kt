package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.exception.IllegalProfileArgumentException
import com.example.user.domain.valueobject.Birth
import com.example.user.domain.valueobject.Category
import com.example.user.domain.valueobject.type.CategorySubType
import com.example.user.domain.valueobject.Gender
import com.example.user.domain.valueobject.Image
import com.example.user.domain.valueobject.Location
import com.example.user.domain.valueobject.MBTI
import com.example.user.domain.valueobject.Nickname
import com.example.user.domain.valueobject.SelfDescription
import jakarta.persistence.CascadeType
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
class Profile(
    member: Member,
    nickname: Nickname,
) : BaseModifiableEntity() {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member

    @Embedded
    var nickname: Nickname = nickname
        protected set

    @Embedded
    var birth: Birth? = null
        protected set

    @Embedded
    var gender : Gender? = null
        protected set

    @Embedded
    var location: Location? = null
        protected set

    @Embedded
    var mbti : MBTI? = null
        protected set

    @Embedded
    var selfDescription: SelfDescription? = null
        protected set

    @Embedded
    var image: Image? = null
        protected set

    @OneToMany(mappedBy = "profile", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableProfileCategories: MutableList<ProfileCategory> = mutableListOf()
    val profileCategories: List<ProfileCategory> get() = mutableProfileCategories.toList()

    // 한번 변경하면 다시 null 로 변경할 수 없도록 하기 위해 nullable 로 설정하지 않음
    fun changeNickname(nickname: Nickname) {
        this.nickname = nickname
    }

    fun changeBirth(birth: Birth) {
        this.birth = birth
    }

    fun changeGender(gender: Gender) {
        this.gender = gender
    }

    fun changeLocation(location : Location) {
        this.location = location
    }

    fun changeMbti(mbti: MBTI) {
        this.mbti = mbti
    }

    fun changeSelfDescription(selfDescription : SelfDescription) {
        this.selfDescription = selfDescription
    }

    // nullable 로 설정하여 기본 이미지 (null) 로 변경 가능
    fun changeImageUrl(image : Image?) {
        this.image = image
    }

    fun changeCategories(categories : List<Category>) {
        if (categories.size > 5) {
            throw IllegalProfileArgumentException("카테고리는 5개 이하로 선택해주세요.")
        }
        mutableProfileCategories.clear()
        categories.forEach { addProfileCategory(it) }
    }

    private fun addProfileCategory(category: Category) {
        mutableProfileCategories.add(ProfileCategory(profile = this, category = category))
    }
}