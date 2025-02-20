package com.example.user.domain.oauth2

import com.example.user.domain.valueobject.type.OAuth2ProviderType

interface OAuth2Fetcher {
    fun getAccessToken(code: String, oAuth2ProviderType: OAuth2ProviderType): String?

    fun getUserAttributes(accessToken: String, oAuth2ProviderType: OAuth2ProviderType): Map<String, Any>?
}