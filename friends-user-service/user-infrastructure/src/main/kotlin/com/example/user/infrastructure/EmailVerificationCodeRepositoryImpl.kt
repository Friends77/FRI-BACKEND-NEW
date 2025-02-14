package com.example.user.infrastructure

import com.example.user.domain.repository.EmailVerificationCodeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class EmailVerificationCodeRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, String>,
    @Value("\${auth.email-code-expiration}") private val codeExpiration: Long,
) : EmailVerificationCodeRepository{

    companion object {
        const val KEY = "emailCode:"
    }

    override fun saveCode(email: String, code: String) {
        redisTemplate.opsForValue().set(getCodeKey(email), code, codeExpiration, TimeUnit.SECONDS)
    }

    override fun getCode(email: String): String? {
        return redisTemplate.opsForValue().get(getCodeKey(email))
    }

    override fun deleteCode(email: String) {
        redisTemplate.delete(getCodeKey(email))
    }

    private fun getCodeKey(email: String) = "$KEY$email"
}