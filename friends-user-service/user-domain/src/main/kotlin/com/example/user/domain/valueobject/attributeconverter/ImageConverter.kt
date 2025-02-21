package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.Image
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class ImageConverter : AttributeConverter<Image, String>{
    override fun convertToDatabaseColumn(attribute: Image?): String? {
        return attribute?.url
    }

    override fun convertToEntityAttribute(dbData: String?): Image? {
        return dbData?.let { Image(it) }
    }
}