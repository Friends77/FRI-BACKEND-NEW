package com.example.message.domain.event

interface EventPublisher {
    fun publishMessageSendEvent(event: MessageSendEvent)
}