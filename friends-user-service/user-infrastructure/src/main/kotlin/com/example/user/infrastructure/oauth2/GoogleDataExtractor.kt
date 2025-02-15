package com.example.user.infrastructure.oauth2

import com.example.user.domain.oauth2.OAuth2DataExtractor
import com.example.user.domain.valueobject.OAuth2ProfileData
import org.springframework.stereotype.Component

@Component
class GoogleDataExtractor : OAuth2DataExtractor {
    override fun extract(attributes: Map<String, Any>): OAuth2ProfileData {
        val name = attributes["name"] as String
        val email = attributes["email"] as String
        val imageUrl = attributes["picture"] as String
        return OAuth2ProfileData(name, email, imageUrl)
    }
}