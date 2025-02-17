package com.example.user.domain.auth

import org.springframework.stereotype.Service

@Service
interface EmailAuthTokenGenerator{
    fun createEmailAuthToken(email: String): String
}