package com.example.friendservernew.member.service

import com.example.friendservernew.member.CreateUserDto
import com.example.friendservernew.member.entity.Member
import com.example.friendservernew.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService (
    private val memberRepository: MemberRepository
){
    fun createUser(createUserDto: CreateUserDto) {
        val email = createUserDto.email
        val encodedPassword = createUserDto.encodedPassword
        val nickname = createUserDto.nickname
        val member =
            Member.createUser(
                email = email,
                nickname = nickname,
                password = encodedPassword,
            )
        memberRepository.save(member)
    }
}