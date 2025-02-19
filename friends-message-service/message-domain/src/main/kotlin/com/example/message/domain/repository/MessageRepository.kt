package com.example.message.domain.repository

import com.example.message.domain.entity.Message

interface MessageRepository {
    fun save(message: Message) : Message
}