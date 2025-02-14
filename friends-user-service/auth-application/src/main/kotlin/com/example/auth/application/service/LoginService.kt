package com.example.auth.application.service

import com.example.auth.application.AtRtDto
import com.example.auth.application.CreateAtRtDto
import com.example.auth.application.LoginDto
import com.example.auth.application.security.userdetails.CustomUserDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoginService(
    private val atRtService: AtRtService,
    private val authenticationManager: AuthenticationManager,
) {

    fun login(loginDto: LoginDto) : AtRtDto {
        val email = loginDto.email
        val password = loginDto.password
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))

        val userDetails = authentication.principal as CustomUserDetails
        val memberId = userDetails.memberId
        val authorities = userDetails.authorities

        val createAtRtDto = CreateAtRtDto(memberId = memberId, authorities = authorities)
        return atRtService.createAtRt(createAtRtDto)
    }
}