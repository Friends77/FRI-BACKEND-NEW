package com.example.friendservernew.security

import com.atelier.server.common.exception.ErrorCode
import com.atelier.server.common.exception.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
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

            val errorResponse =
                ErrorResponse.of(
                    ErrorCode.UNAUTHORIZED,
                    authException?.message,
                )
            writer.write(errorResponse.toJson())
        }
    }
}

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?,
    ) {
        response?.apply {
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = "UTF-8"
            status = HttpServletResponse.SC_FORBIDDEN

            val errorResponse =
                ErrorResponse.of(
                    ErrorCode.FORBIDDEN,
                    accessDeniedException?.message,
                )
            writer.write(errorResponse.toJson())
        }
    }
}
