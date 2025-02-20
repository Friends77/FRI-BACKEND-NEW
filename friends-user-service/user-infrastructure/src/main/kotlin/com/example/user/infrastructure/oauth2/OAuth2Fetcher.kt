package com.example.user.infrastructure.oauth2

import com.example.user.domain.oauth2.OAuth2Fetcher
import com.example.user.domain.valueobject.type.OAuth2ProviderType
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.nio.charset.StandardCharsets


@Component
class OAuth2Fetcher(
    private val oAuth2Properties: OAuth2Properties
) : OAuth2Fetcher {
    override fun getAccessToken(code: String, oAuth2ProviderType: OAuth2ProviderType): String? {
        val oAuth2Property = oAuth2Properties.get(oAuth2ProviderType)
        val result = WebClient
            .create()
            .post()
            .uri(oAuth2Property.tokenUrl)
            .headers {
                it.setBasicAuth(oAuth2Property.clientId, oAuth2Property.clientSecret)
                it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                it.accept = listOf(MediaType.APPLICATION_JSON)
                it.acceptCharset = listOf(StandardCharsets.UTF_8)
            }.bodyValue(accessTokenRequestForm(code, oAuth2ProviderType))
            .retrieve()
            .bodyToMono(OAuth2AccessTokenResponseDto::class.java)
            .block()
        return result?.accessToken
    }

    override fun getUserAttributes(accessToken: String, oAuth2ProviderType: OAuth2ProviderType): Map<String, Any>? {
        val oAuth2Property = oAuth2Properties.get(oAuth2ProviderType)
        return WebClient
            .create()
            .post()
            .uri(oAuth2Property.userInfoUrl)
            .headers { headers ->
                headers.setBearerAuth(accessToken)
            }.retrieve()
            .bodyToMono<Map<String, Any>>()
            .block()
    }

    private fun accessTokenRequestForm(
        code: String,
        oAuth2ProviderType: OAuth2ProviderType,
    ): MultiValueMap<String, String> {
        val form = LinkedMultiValueMap<String, String>()
        val oAuth2Property = oAuth2Properties.get(oAuth2ProviderType)
        form.add("code", code)
        form.add("grant_type", "authorization_code")
        form.add("redirect_uri", oAuth2Property.redirectUrl)
        return form
    }

    data class OAuth2AccessTokenResponseDto(
        @JsonProperty("access_token") val accessToken: String,
    )
}