package com.example.user.infrastructure.oauth2

import com.example.user.domain.oauth2.OAuth2DataExtractor
import com.example.user.domain.oauth2.OAuth2DataExtractorFactory
import com.example.user.domain.valueobject.OAuth2Provider
import org.springframework.stereotype.Component

@Component
class OAuth2ProfileDataExtractorFactory : OAuth2DataExtractorFactory {
    override fun getExtractor(oAuth2Provider: OAuth2Provider): OAuth2DataExtractor {
        return when (oAuth2Provider) {
            OAuth2Provider.GOOGLE -> GoogleDataExtractor()
            OAuth2Provider.NAVER -> NaverDataExtractor()
        }
    }
}