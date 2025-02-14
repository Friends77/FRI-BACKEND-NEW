package com.example.user.domain.repository

interface EmailVerificationCodeRepository {
    fun saveCode(email: String, code: String)

    fun getCode(email: String): String?

    fun deleteCode(email: String)
}

