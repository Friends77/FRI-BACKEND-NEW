package com.example.chat.domain.valueobject

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

//  user-service 에서 profile 업데이트 이벤트가 유효하게 발생한다고 가정
@Embeddable
class Profile(
    nickname: String,
    profileImage: String?
) {
    @Column(nullable = false)
    var nickname: String = nickname
        private set

    // 프로필 이미지가 null 일 경우 기본 이미지
    @Column(nullable = true)
    var profileImage: String? = profileImage
        private set

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changeProfileImage(profileImage: String) {
        this.profileImage = profileImage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (nickname != other.nickname) return false
        if (profileImage != other.profileImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nickname.hashCode()
        result = 31 * result + profileImage.hashCode()
        return result
    }
}