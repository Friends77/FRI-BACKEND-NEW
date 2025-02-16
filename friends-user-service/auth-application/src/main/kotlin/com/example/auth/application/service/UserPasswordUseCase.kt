package com.example.auth.application.service

import com.example.auth.application.ResetPasswordDto
import com.example.user.domain.service.EmailAuthTokenService
import com.example.user.domain.service.UserPasswordService
import com.example.user.domain.validator.UserRegisterValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserPasswordUseCase (
    private val userPasswordService: UserPasswordService,
    private val userRegisterValidator: UserRegisterValidator,
    private val emailAuthTokenService: EmailAuthTokenService
){
    fun resetPassword(resetPasswordDto: ResetPasswordDto) {
        val emailAuthToken = resetPasswordDto.emailAuthToken
        val email = resetPasswordDto.email
        emailAuthTokenService.validateEmailAuthToken(emailAuthToken, email)

        val newPassword = resetPasswordDto.newPassword
        userRegisterValidator.validatePasswordPattern(newPassword)

        userPasswordService.resetPassword(email, newPassword)
    }
}