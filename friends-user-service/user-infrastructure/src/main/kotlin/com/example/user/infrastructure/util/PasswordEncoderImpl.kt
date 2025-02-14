package com.example.user.infrastructure.util

import com.example.user.domain.util.PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoderImpl : PasswordEncoder {
    private val encoder: BCryptPasswordEncoder
        get() = BCryptPasswordEncoder()

    override fun encode(rawPassword: String): String {
        return encoder.encode(rawPassword)
    }

    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        return encoder.matches(rawPassword, encodedPassword)
    }
}