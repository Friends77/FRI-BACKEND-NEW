package com.example.user.domain.service

import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.util.PasswordEncoder
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
        val member = Member.createUser(email = email, password = passwordEncoder.encode(password))
        val profile = Profile(member = member, nickname = nickname)
        memberRepository.save(member)
        profileRepository.save(profile)
        return member
    }
}