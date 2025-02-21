package com.example.auth.application.service

import com.example.auth.application.dto.ResetPasswordDto
import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.exception.MemberNotFoundException
import com.example.user.domain.exception.PasswordEqualLastPasswordException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.validator.EmailAuthTokenValidator
import com.example.user.domain.valueobject.EncodedPassword
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserResetPasswordService (
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository,
    private val emailAuthTokenValidator: EmailAuthTokenValidator
){
    fun resetPassword(resetPasswordDto: ResetPasswordDto) {
        val emailAuthToken = resetPasswordDto.emailAuthToken
        val email = resetPasswordDto.email
        val newPassword = resetPasswordDto.newPassword

        emailAuthTokenValidator.validateEmailAuthToken(emailAuthToken, email)

        val member = memberRepository.findByEmail(Email(email)) ?: throw MemberNotFoundException("$email 로 가입된 회원이 없습니다.")

        if (passwordEncoder.matches(newPassword, member.password?.value)) { throw PasswordEqualLastPasswordException() }

        member.changePassword(EncodedPassword(passwordEncoder.encode(newPassword)))
    }
}