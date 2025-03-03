package com.example.chat.domain.entity

import com.example.chat.domain.entity.base.BaseModifiableEntity
import com.example.chat.domain.valueobject.Category
import com.example.chat.domain.valueobject.Title
import com.example.chat.domain.valueobject.converter.TitleConverter
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity
class ChatRoom(
    managerId : UUID,
    title : Title,
    categories: List<Category>
) : BaseModifiableEntity() {
    companion object

    @Column(nullable = false)
    var managerId = managerId
        private set

    @Column(name = "title", nullable = false)
    @Convert(converter = TitleConverter::class)
    var title = title
        private set

    @OneToMany(mappedBy = "chatRoom", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableChatRoomCategories: MutableList<ChatRoomCategory> =
        categories.map { ChatRoomCategory(this, it) }.toMutableList()
    val chatRoomCategories: List<ChatRoomCategory> get() = mutableChatRoomCategories.toList()

    @OneToMany(mappedBy = "chatRoom", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableChatRoomMembers: MutableList<ChatRoomMember> = mutableListOf()
    val chatRoomMembers: List<ChatRoomMember> get() = mutableChatRoomMembers.toList()

    var description: String? = null
        private set

    var profileImageUrl : String? = null
        private set

    var participantCount: Int = 0
        private set

    fun setNewManager(managerId: UUID) {
        this.managerId = managerId
    }

    fun changeTitle(title: Title) {
        this.title = title
    }

    fun changeCategories(categories: List<Category>) {
        mutableChatRoomCategories.clear()
        categories.forEach { addCategory(it) }
    }

    fun changeDescription(description: String) {
        this.description = description
    }

    fun changeProfileImageUrl(profileImageUrl: String?) {
        this.profileImageUrl = profileImageUrl
    }

    fun increaseParticipantCount() {
        participantCount++
    }

    fun decreaseParticipantCount() {
        participantCount--
    }

    private fun addCategory(category: Category) {
        mutableChatRoomCategories.add(ChatRoomCategory(this, category))
    }

    fun addChatRoomMember(chatRoomMember: ChatRoomMember) {
        mutableChatRoomMembers.add(chatRoomMember)
    }
}