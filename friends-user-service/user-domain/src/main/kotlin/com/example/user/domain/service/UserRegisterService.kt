package com.example.user.domain.service

import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.EncodedPassword
import org.springframework.stereotype.Service

@Service
class UserRegisterService(
    private val memberRepository: MemberRepository,
    private val profileRepository: ProfileRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun registerUser(
        nickname: String,
        email: String,
        password : String,
    ) : Member {
        val encodedPassword = EncodedPassword(passwordEncoder.encode(password))
        val member = Member.createUser(Email(email), encodedPassword)
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return member
    }
}