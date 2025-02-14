package com.example.user.domain.util

interface PasswordEncoder {
    fun encode(rawPassword: String): String

    fun matches(rawPassword: String, encodedPassword: String): Boolean
}