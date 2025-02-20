package com.example.user.infrastructure.oauth2

import com.example.user.domain.valueobject.type.OAuth2ProviderType
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "oauth2")
data class OAuth2Properties(
    val google: Provider,
    val naver: Provider
) {
    fun get(oAuth2ProviderType: OAuth2ProviderType): Provider {
        return when (oAuth2ProviderType) {
            OAuth2ProviderType.GOOGLE -> google
            OAuth2ProviderType.NAVER -> naver
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