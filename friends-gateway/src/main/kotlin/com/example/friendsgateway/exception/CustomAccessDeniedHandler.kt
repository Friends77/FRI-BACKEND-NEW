package com.example.friendsgateway.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

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

            writer.write(
                """
                {
                    "message": "${accessDeniedException?.message}",
                    "status": ${HttpServletResponse.SC_FORBIDDEN}
                }
                """.trimIndent()
            )
        }
    }
}