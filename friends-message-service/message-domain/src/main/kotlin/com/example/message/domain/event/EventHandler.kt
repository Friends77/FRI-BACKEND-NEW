package com.example.message.domain.event

interface EventHandler {
    fun messageReceiveEventHandler(event: MessageReceiveEvent)
}