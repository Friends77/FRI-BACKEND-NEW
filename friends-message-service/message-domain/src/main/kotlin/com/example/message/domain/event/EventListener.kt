package com.example.message.domain.event

interface EventListener {
    fun consumeMessageReceiveEvent(payload : String)
}