package com.example.auth.application.service

import com.example.auth.application.dto.AtRtDto
import com.example.auth.application.mapper.AuthMapper
import com.example.auth.application.dto.LoginDto
import com.example.user.domain.auth.AtRtGenerator
import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.exception.LoginFailedException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.valueobject.Email
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLoginService(
    private val atRtGenerator: AtRtGenerator,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(loginDto: LoginDto) : AtRtDto {
        val email = loginDto.email
        val password = loginDto.password

        val member = memberRepository.findByEmailWithAuthorities(Email(email))
            ?: throw LoginFailedException("해당 이메일 [${email}]로 가입된 회원이 없습니다.")

        if (member.isOAuth2User || member.password == null)
            throw LoginFailedException("해당 이메일 [${email}]로 가입된 회원은 비밀번호로 로그인할 수 없습니다. \n 소셜 로그인을 이용해주세요.")

        if (!passwordEncoder.matches(password, member.password!!.value))
            throw LoginFailedException("이메일 또는 비밀번호가 일치하지 않습니다.")

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        val atRt = atRtGenerator.createAtRt(memberId, authorities)
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}