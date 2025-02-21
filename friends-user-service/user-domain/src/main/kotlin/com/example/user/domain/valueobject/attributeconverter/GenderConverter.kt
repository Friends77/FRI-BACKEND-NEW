package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.Gender
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class GenderConverter : AttributeConverter<Gender, String> {
    override fun convertToDatabaseColumn(attribute: Gender?): String? {
        return attribute?.type?.name
    }

    override fun convertToEntityAttribute(dbData: String?): Gender? {
        return dbData?.let { Gender(it) }
    }
}