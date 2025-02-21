package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.Nickname
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class NicknameConverter : AttributeConverter<Nickname, String>{
    override fun convertToDatabaseColumn(attribute: Nickname?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): Nickname? {
        return dbData?.let { Nickname(it) }
    }
}