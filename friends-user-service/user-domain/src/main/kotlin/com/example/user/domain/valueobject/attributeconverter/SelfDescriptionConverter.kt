package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.SelfDescription
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class SelfDescriptionConverter : AttributeConverter<SelfDescription, String> {
    override fun convertToDatabaseColumn(attribute: SelfDescription?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): SelfDescription? {
        return dbData?.let { SelfDescription(it) }
    }
}