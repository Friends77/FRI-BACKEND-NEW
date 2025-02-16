package com.example.user.domain.valueobject

enum class JwtKey(
    val value : String
) {
    EMAIL("email"),
    AUTHORITIES("authorities"),
    TYPE("type"),
    MEMBER_ID("memberId");
}