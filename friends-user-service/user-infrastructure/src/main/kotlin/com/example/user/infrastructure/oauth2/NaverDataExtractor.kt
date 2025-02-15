package com.example.user.infrastructure.oauth2

import com.example.user.domain.oauth2.OAuth2DataExtractor
import com.example.user.domain.valueobject.OAuth2ProfileData
import org.springframework.stereotype.Component

@Component
class NaverDataExtractor : OAuth2DataExtractor {
    override fun extract(attributes: Map<String, Any>): OAuth2ProfileData {
        val response = attributes["response"] as Map<String, Any>
        val name = response["name"] as String
        val email = response["email"] as String
        val imageUrl = response["profile_image"] as String
        return OAuth2ProfileData(name, email, imageUrl)
    }
}