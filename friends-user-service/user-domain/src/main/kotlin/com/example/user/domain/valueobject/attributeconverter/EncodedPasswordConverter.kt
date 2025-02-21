package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.EncodedPassword
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class EncodedPasswordConverter : AttributeConverter<EncodedPassword, String>{
    override fun convertToDatabaseColumn(attribute: EncodedPassword?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): EncodedPassword? {
        return dbData?.let { EncodedPassword(it) }
    }
}