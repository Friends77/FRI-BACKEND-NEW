package com.example.user.domain.service

import com.example.user.domain.exception.MemberNotFoundByEmailException
import com.example.user.domain.exception.OAuth2UserPasswordChangeException
import com.example.user.domain.exception.PasswordEqualLastPasswordException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.util.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserPasswordService (
    private val passwordEncoder: PasswordEncoder,
    private val memberRepository: MemberRepository
){
    fun resetPassword(
        email: String,
        newPassword : String
    ) {
        val member = memberRepository.findByEmail(email) ?: throw MemberNotFoundByEmailException()
        if (member.isOAuth2User) {
            throw OAuth2UserPasswordChangeException()
        }
        if (passwordEncoder.matches(newPassword, member.password!!)) {
            throw PasswordEqualLastPasswordException()
        }

        member.changePassword(passwordEncoder.encode(newPassword))
    }
}