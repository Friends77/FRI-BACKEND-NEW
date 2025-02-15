package com.example.user.domain.oauth2

import com.example.user.domain.valueobject.OAuth2ProfileData

interface OAuth2DataExtractor {
    fun extract(data: Map<String, Any>): OAuth2ProfileData
}