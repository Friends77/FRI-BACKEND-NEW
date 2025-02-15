package com.example.user.domain.oauth2

import com.example.user.domain.valueobject.OAuth2Provider

interface OAuth2DataExtractorFactory {
    fun getExtractor(oAuth2Provider: OAuth2Provider): OAuth2DataExtractor
}