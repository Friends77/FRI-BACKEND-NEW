package com.example.user.domain.oauth2

import com.example.user.domain.valueobject.type.OAuth2ProviderType

interface OAuth2DataExtractorFactory {
    fun getExtractor(oAuth2ProviderType: OAuth2ProviderType): OAuth2DataExtractor
}