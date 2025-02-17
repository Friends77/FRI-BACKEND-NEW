package com.example.user.domain.service

import com.example.user.domain.auth.AtRtGenerator
import com.example.user.domain.auth.JwtClaimReader
import com.example.user.domain.exception.LoginFailedException
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.validator.AtRtValidator
import com.example.user.domain.valueobject.AtRt
import com.example.user.domain.valueobject.JwtKey
import org.springframework.stereotype.Service

@Service
class UserLoginService(
    private val atRtGenerator: AtRtGenerator,
    private val atRtValidator: AtRtValidator,
    private val jwtClaimReader: JwtClaimReader,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(
        email: String,
        password: String,
    ) : AtRt{
        val member = memberRepository.findByEmailWithAuthorities(email) ?: throw LoginFailedException()

        if (!passwordEncoder.matches(password, member.password!!)) throw LoginFailedException()

        val memberId = member.id
        val authorities = member.authorities.map { it.role }

        return atRtGenerator.createAtRt(memberId, authorities)
    }

    fun refresh(
        refreshToken: String
    ) : AtRt {
        atRtValidator.validateRefreshToken(refreshToken)

        val memberId = jwtClaimReader.getMemberId(refreshToken) ?: throw MissingJwtPayloadException(JwtKey.MEMBER_ID.value)
        val authorities = jwtClaimReader.getAuthorities(refreshToken) ?: throw MissingJwtPayloadException(JwtKey.AUTHORITIES.value)

        return atRtGenerator.createAtRt(memberId, authorities)
    }
}