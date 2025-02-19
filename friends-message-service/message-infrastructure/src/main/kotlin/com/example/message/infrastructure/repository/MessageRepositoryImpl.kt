package com.example.message.infrastructure.repository

import com.example.message.domain.entity.Message
import com.example.message.domain.repository.MessageRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MessageRepositoryImpl : JpaRepository<Message, UUID>, MessageRepository {
}