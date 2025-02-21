package com.example.user.domain.valueobject.attributeconverter

import com.example.user.domain.valueobject.Birth
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.LocalDate

@Converter
class BirthConverter : AttributeConverter<Birth, LocalDate> {
    override fun convertToDatabaseColumn(attribute: Birth?): LocalDate? {
        return attribute?.localDate
    }

    override fun convertToEntityAttribute(dbData : LocalDate?): Birth? {
        return dbData?.let { Birth(it) }
    }
}