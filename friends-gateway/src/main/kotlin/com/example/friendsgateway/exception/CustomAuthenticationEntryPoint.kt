package com.example.friendsgateway.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?,
    ) {
        response?.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = "UTF-8"
            status = HttpServletResponse.SC_UNAUTHORIZED

            writer.write(
                """
                {
                    "message": "${authException?.message}",
                    "status": ${HttpServletResponse.SC_UNAUTHORIZED}
                }
                """.trimIndent()
            )
        }
    }
}