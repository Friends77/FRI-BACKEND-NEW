package com.example.message.domain.entity

import com.example.message.domain.entity.base.BaseModifiableEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.UUID

@Entity
class LastReadMessage(
    memberId : UUID,
    lastReadMessageId : UUID
) : BaseModifiableEntity() {
    @Column(nullable = false, unique = true)
    val memberId: UUID = memberId

    @Column(nullable = false)
    var lastReadMessageId: UUID = lastReadMessageId
        protected set

    fun updateLastReadMessageId(lastReadMessageId: UUID) {
        this.lastReadMessageId = lastReadMessageId
    }
}