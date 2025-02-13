package com.example.auth.application.config

import com.example.auth.application.security.userdetails.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SpringSecurityConfig (
    private val customUserDetailsService: CustomUserDetailsService
){
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider =
        DaoAuthenticationProvider().apply {
            setUserDetailsService(customUserDetailsService)
            setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager = authConfig.authenticationManager
}