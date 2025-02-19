package com.example.message.infrastructure.repository

import com.example.message.domain.entity.LastReadMessage
import com.example.message.domain.repository.LastReadMessageRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LastReadMessageRepositoryImpl : JpaRepository<LastReadMessage, UUID> , LastReadMessageRepository {
}