package com.example.auth.application.service

import com.example.auth.application.dto.AtRtDto
import com.example.auth.application.dto.RefreshDto
import com.example.auth.application.mapper.AuthMapper
import com.example.user.domain.auth.AtRtGenerator
import com.example.user.domain.auth.JwtClaimReader
import com.example.user.domain.exception.MissingJwtPayloadException
import com.example.user.domain.validator.AtRtValidator
import com.example.user.domain.valueobject.JwtKey
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserRefreshService(
    private val atRtValidator: AtRtValidator,
    private val jwtClaimReader: JwtClaimReader,
    private val atRtGenerator: AtRtGenerator
) {
    fun refresh(refreshDto: RefreshDto) : AtRtDto {
        val refreshToken = refreshDto.refreshToken

        atRtValidator.validateRefreshToken(refreshToken)

        val memberId = jwtClaimReader.getMemberId(refreshToken)
            ?: throw MissingJwtPayloadException("${JwtKey.MEMBER_ID.value} 가 refresh token 에 존재하지 않습니다.")
        val authorities = jwtClaimReader.getAuthorities(refreshToken)
            ?: throw MissingJwtPayloadException("${JwtKey.AUTHORITIES.value} 가 refresh token 에 존재하지 않습니다.")

        val atRt = atRtGenerator.createAtRt(memberId, authorities)
        return AuthMapper.atRtToAtRtDto(atRt)
    }
}