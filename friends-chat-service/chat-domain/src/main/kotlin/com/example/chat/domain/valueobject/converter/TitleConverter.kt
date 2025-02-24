package com.example.chat.domain.valueobject.converter

import com.example.chat.domain.valueobject.Title
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class TitleConverter : AttributeConverter<Title, String>{
    override fun convertToDatabaseColumn(attribute: Title?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): Title? {
        return dbData?.let { Title(it) }
    }
}