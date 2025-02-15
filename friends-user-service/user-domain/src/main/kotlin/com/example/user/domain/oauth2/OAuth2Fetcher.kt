package com.example.user.domain.oauth2

import com.example.user.domain.valueobject.OAuth2Provider

interface OAuth2Fetcher {
    fun getAccessToken(code: String, oAuth2Provider: OAuth2Provider): String?

    fun getUserAttributes(accessToken: String, oAuth2Provider: OAuth2Provider): Map<String, Any>?
}