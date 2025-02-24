package com.example.chat.domain.valueobject.converter

import com.example.chat.domain.valueobject.Category
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class CategoryConverter : AttributeConverter<Category, String> {
    override fun convertToDatabaseColumn(attribute: Category?): String? {
        return attribute?.type?.name
    }

    override fun convertToEntityAttribute(dbData: String?): Category? {
        return dbData?.let { Category(it) }
    }
}