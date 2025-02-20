package com.example.user.domain.validator

import com.example.user.domain.valueobject.type.JwtType

interface JwtValidator {
    fun isValid(token : String, type : JwtType) : Boolean
}