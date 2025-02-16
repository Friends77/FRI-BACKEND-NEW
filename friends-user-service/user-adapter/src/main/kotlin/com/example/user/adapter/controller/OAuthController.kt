package com.example.user.adapter.controller

import com.example.auth.application.service.UserOAuth2LoginUserCase
import com.example.user.adapter.AdapterMapper
import com.example.user.adapter.LoginResponseDto
import com.example.user.adapter.OAuth2LoginRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/global/oauth")
class OAuthController (
    private val userOAuth2LoginUserCase: UserOAuth2LoginUserCase
){
    @PostMapping("/login")
    fun oauth2Login(@RequestBody oauth2LoginRequestDto: OAuth2LoginRequestDto) : ResponseEntity<LoginResponseDto> {
        val oAuth2LoginDto = AdapterMapper.oauth2LoginRequestDtoToOAuth2LoginDto(oauth2LoginRequestDto)
        val atRtDto = userOAuth2LoginUserCase.loginByOAuth2(oAuth2LoginDto)
        val loginResponseDto = AdapterMapper.atRtDtoToLoginResponseDto(atRtDto)
        return ResponseEntity.ok(loginResponseDto)
    }
}