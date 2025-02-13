package com.example.friendsgateway.config

import com.example.friendsgateway.exception.CustomAccessDeniedHandler
import com.example.friendsgateway.exception.CustomAuthenticationEntryPoint
import com.example.friendsgateway.filter.Authority
import com.example.friendsgateway.filter.AuthorizationHeaderFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SpringSecurityConfig (
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val authorizationHeaderFilter: AuthorizationHeaderFilter
){
    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }

        http
            .addFilterBefore(authorizationHeaderFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it
                    .accessDeniedHandler(customAccessDeniedHandler)
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
            }

        http.authorizeHttpRequests {
            it
                .requestMatchers(
                    "/user-service/global/**",
                    "/chat-service/global/**",
                    "/message-service/global/**",
                ).permitAll()
                .requestMatchers(
                    "/user-service/user/**",
                    "/chat-service/user/**",
                    "/message-service/user/**",
                )
                .hasAuthority(Authority.ROLE_USER.name)
                .requestMatchers(
                    "/user-service/admin/**",
                    "/chat-service/admin/**",
                    "/message-service/admin/**",
                )
                .hasAuthority(Authority.ROLE_ADMIN.name)
                .anyRequest()
                .authenticated()
        }

        return http.build()
    }
}