package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.Email
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class EmailConverter : AttributeConverter<Email, String> {
    override fun convertToDatabaseColumn(attribute: Email?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): Email? {
        return dbData?.let { Email(it) }
    }
}