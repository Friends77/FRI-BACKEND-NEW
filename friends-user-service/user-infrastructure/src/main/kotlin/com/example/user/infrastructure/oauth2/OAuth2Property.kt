package com.example.user.infrastructure.oauth2

import com.example.user.domain.valueobject.OAuth2Provider
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "oauth2")
data class OAuth2Properties(
    val google: Provider,
    val naver: Provider
) {
    fun get(oAuth2Provider: OAuth2Provider): Provider {
        return when (oAuth2Provider) {
            OAuth2Provider.GOOGLE -> google
            OAuth2Provider.NAVER -> naver
        }
    }

    data class Provider(
        val clientId: String,
        val clientSecret: String,
        val redirectUrl: String,
        val tokenUrl: String,
        val userInfoUrl: String
    )
}