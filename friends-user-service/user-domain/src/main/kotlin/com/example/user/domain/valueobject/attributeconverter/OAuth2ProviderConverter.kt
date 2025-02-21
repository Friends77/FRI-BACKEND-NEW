package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.OAuth2Provider
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class OAuth2ProviderConverter : AttributeConverter<OAuth2Provider, String>{
    override fun convertToDatabaseColumn(attribute: OAuth2Provider?): String? {
        return attribute?.type?.name
    }

    override fun convertToEntityAttribute(dbData: String?): OAuth2Provider? {
        return dbData?.let { OAuth2Provider(it) }
    }
}