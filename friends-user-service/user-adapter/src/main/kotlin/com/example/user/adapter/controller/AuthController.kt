package com.example.user.adapter.controller

import com.auth.application.RegisterRequestDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    @PostMapping("/register")
    fun register(@RequestBody registerRequestDto: RegisterRequestDto) {

    }
}