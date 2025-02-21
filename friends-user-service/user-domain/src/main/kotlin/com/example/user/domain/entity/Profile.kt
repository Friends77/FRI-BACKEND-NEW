package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.exception.IllegalProfileArgumentException
import com.example.user.domain.valueobject.Birth
import com.example.user.domain.valueobject.Category
import com.example.user.domain.valueobject.Gender
import com.example.user.domain.valueobject.Image
import com.example.user.domain.valueobject.Location
import com.example.user.domain.valueobject.MBTI
import com.example.user.domain.valueobject.Nickname
import com.example.user.domain.valueobject.SelfDescription
import com.example.user.domain.valueobject.attributeconverter.BirthConverter
import com.example.user.domain.valueobject.attributeconverter.GenderConverter
import com.example.user.domain.valueobject.attributeconverter.ImageConverter
import com.example.user.domain.valueobject.attributeconverter.MBTIConverter
import com.example.user.domain.valueobject.attributeconverter.NicknameConverter
import com.example.user.domain.valueobject.attributeconverter.SelfDescriptionConverter
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity
class Profile(
    memberId: UUID,
    nickname: Nickname,
) : BaseModifiableEntity() {
    @Column(name = "member_id", nullable = false, updatable = false, unique = true)
    val memberId: UUID = memberId
    
    @Column(name = "nickname", nullable = false)
    @Convert(converter = NicknameConverter::class)
    var nickname: Nickname = nickname
        protected set

    @Column(name = "birth", nullable = true)
    @Convert(converter = BirthConverter::class)
    var birth: Birth? = null
        protected set

    @Column(name = "gender", nullable = true)
    @Convert(converter = GenderConverter::class)
    var gender : Gender? = null
        protected set

    @Embedded
    var location: Location? = null
        protected set

    @Column(name = "mbti", nullable = true)
    @Convert(converter = MBTIConverter::class)
    var mbti : MBTI? = null
        protected set

    @Column(name = "self_description", nullable = true)
    @Convert(converter = SelfDescriptionConverter::class)
    var selfDescription: SelfDescription? = null
        protected set

    @Column(name = "image_url", nullable = true)
    @Convert(converter = ImageConverter::class)
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