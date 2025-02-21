package com.example.auth.application.service

import com.example.auth.application.dto.ResetPasswordDto
import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.exception.MemberNotFoundException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.service.UserPasswordService
import com.example.user.domain.validator.EmailAuthTokenValidator
import com.example.user.domain.validator.UserRegisterValidator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserPasswordUseCase (
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository,
    private val userRegisterValidator: UserRegisterValidator,
    private val emailAuthTokenValidator: EmailAuthTokenValidator
){
    fun resetPassword(resetPasswordDto: ResetPasswordDto) {
        val emailAuthToken = resetPasswordDto.emailAuthToken
        val email = resetPasswordDto.email
        val newPassword = resetPasswordDto.newPassword

        emailAuthTokenValidator.validateEmailAuthToken(emailAuthToken, email)

        memberRepository.findByEmail(email) ?: throw MemberNotFoundException("")

        userRegisterValidator.validatePasswordPattern(newPassword)

        userPasswordService.resetPassword(email, newPassword)
    }
}