package com.example.user.domain.entity.auth

interface VerificationMailSender {
    fun sendVerificationMail(to: String)
}
