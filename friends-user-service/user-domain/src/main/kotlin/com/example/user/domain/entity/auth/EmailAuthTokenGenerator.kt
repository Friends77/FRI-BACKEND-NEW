package com.example.user.domain.entity.auth

import org.springframework.stereotype.Service

@Service
interface EmailAuthTokenGenerator{
    fun createEmailAuthToken(email: String): String
}