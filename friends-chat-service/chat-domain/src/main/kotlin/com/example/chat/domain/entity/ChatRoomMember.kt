package com.example.chat.domain.entity

import com.example.chat.domain.entity.base.BaseTimeEntity
import com.example.chat.domain.valueobject.Profile
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import java.util.UUID

@Entity
class ChatRoomMember(
    chatRoom: ChatRoom,
    memberId : UUID,
    profile: Profile,
) : BaseTimeEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", updatable = false, nullable = false)
    val chatRoom: ChatRoom = chatRoom

    @Column(name = "member_id", nullable = false)
    val memberId: UUID = memberId

    var isCustomProfile = false
        protected set

    @Embedded
    var profile: Profile = profile

    fun changeProfileNickname(nickname: String) {
        profile.changeNickname(nickname)
    }

    fun changeProfileImageUrl(profileImage: String) {
        profile.changeProfileImage(profileImage)
    }
}