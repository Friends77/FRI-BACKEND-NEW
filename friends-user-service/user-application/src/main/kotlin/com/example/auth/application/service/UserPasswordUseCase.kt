package com.example.auth.application.service

import com.example.auth.application.dto.ResetPasswordDto
import com.example.user.domain.service.UserPasswordService
import com.example.user.domain.validator.EmailAuthTokenValidator
import com.example.user.domain.validator.UserRegisterValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserPasswordUseCase (
    private val userPasswordService: UserPasswordService,
    private val userRegisterValidator: UserRegisterValidator,
    private val emailAuthTokenValidator: EmailAuthTokenValidator
){
    fun resetPassword(resetPasswordDto: ResetPasswordDto) {
        val emailAuthToken = resetPasswordDto.emailAuthToken
        val email = resetPasswordDto.email
        emailAuthTokenValidator.validateEmailAuthToken(emailAuthToken, email)

        val newPassword = resetPasswordDto.newPassword
        userRegisterValidator.validatePasswordPattern(newPassword)

        userPasswordService.resetPassword(email, newPassword)
    }
}