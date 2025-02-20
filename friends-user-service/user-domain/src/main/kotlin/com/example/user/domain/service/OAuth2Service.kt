package com.example.user.domain.service

import com.example.user.domain.exception.OAuth2DataExtractException
import com.example.user.domain.exception.OAuth2FetchFailedException
import com.example.user.domain.oauth2.OAuth2DataExtractorFactory
import com.example.user.domain.oauth2.OAuth2Fetcher
import com.example.user.domain.valueobject.OAuth2ProfileData
import com.example.user.domain.valueobject.type.OAuth2ProviderType
import org.springframework.stereotype.Service

@Service
class OAuth2Service(
    private val oAuth2Fetcher: OAuth2Fetcher,
    private val oAuth2DataExtractorFactory: OAuth2DataExtractorFactory,
) {
    fun getUserProfile(
        code: String,
        oAuth2ProviderType: OAuth2ProviderType
    ): OAuth2ProfileData {
        val accessToken = oAuth2Fetcher.getAccessToken(code, oAuth2ProviderType)
            ?: throw OAuth2FetchFailedException(oAuth2ProviderType)
        val attributes = oAuth2Fetcher.getUserAttributes(accessToken, oAuth2ProviderType)
            ?: throw OAuth2FetchFailedException(oAuth2ProviderType)

        val extractor = try {
            oAuth2DataExtractorFactory.getExtractor(oAuth2ProviderType)
        } catch (e: Exception) {
            throw OAuth2DataExtractException()
        }
        return extractor.extract(attributes)
    }
}