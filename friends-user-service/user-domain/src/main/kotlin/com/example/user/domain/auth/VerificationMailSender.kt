package com.example.user.domain.auth

interface VerificationMailSender {
    fun sendVerificationMail(to: String)
}
