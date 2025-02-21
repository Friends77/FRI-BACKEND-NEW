package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.Category
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