package com.example.user.domain.validator

import com.example.user.domain.valueobject.JwtType

interface JwtValidator {
    fun isValid(token : String, type : JwtType) : Boolean
}