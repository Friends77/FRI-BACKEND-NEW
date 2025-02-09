package com.atelier.server.auth.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class MailVerificationCodeRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    @Value("\${auth.email-code-expiration}") private val emailCodeExpiration: Long,
) {
    companion object {
        const val key = "emailCode:"
    }
    fun save(
        email: String,
        code: String,
    ) {
        redisTemplate.opsForValue().set(getCodeKey(email), code, emailCodeExpiration, TimeUnit.SECONDS)
    }

    private fun getCodeKey(email: String) = "$key$email"

    fun getCode(email: String): String? = redisTemplate.opsForValue().get(getCodeKey(email))

    fun deleteCode(email: String) {
        redisTemplate.delete(getCodeKey(email))
    }
}