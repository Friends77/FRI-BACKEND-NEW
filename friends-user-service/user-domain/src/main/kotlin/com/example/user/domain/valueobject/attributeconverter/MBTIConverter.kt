package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.MBTI
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class MBTIConverter : AttributeConverter<MBTI, String> {
    override fun convertToDatabaseColumn(attribute: MBTI?): String? {
        return attribute?.type?.name
    }

    override fun convertToEntityAttribute(dbData: String?): MBTI? {
        return dbData?.let { MBTI(it) }
    }
}