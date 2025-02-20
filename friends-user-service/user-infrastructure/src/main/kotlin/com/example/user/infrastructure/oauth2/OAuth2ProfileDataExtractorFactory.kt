package com.example.user.infrastructure.oauth2

import com.example.user.domain.oauth2.OAuth2DataExtractor
import com.example.user.domain.oauth2.OAuth2DataExtractorFactory
import com.example.user.domain.valueobject.type.OAuth2ProviderType
import org.springframework.stereotype.Component

@Component
class OAuth2ProfileDataExtractorFactory : OAuth2DataExtractorFactory {
    override fun getExtractor(oAuth2ProviderType: OAuth2ProviderType): OAuth2DataExtractor {
        return when (oAuth2ProviderType) {
            OAuth2ProviderType.GOOGLE -> GoogleDataExtractor()
            OAuth2ProviderType.NAVER -> NaverDataExtractor()
        }
    }
}