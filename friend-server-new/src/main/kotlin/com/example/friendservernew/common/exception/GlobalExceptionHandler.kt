package com.example.friendservernew.common.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(ex: CustomException): ResponseEntity<Any> {
        log.error(ex.message)
        return ResponseEntity
            .status(ex.errorCode.httpStatus)
            .body(ErrorResponse.of(ex.errorCode, ex.message))
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<Any> {
        log.error(ex.message)
        return ResponseEntity
            .status(ErrorCode.UNAUTHORIZED.httpStatus)
            .body(ErrorResponse.of(ErrorCode.UNAUTHORIZED, ex.message))
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException): ResponseEntity<Any> {
        log.error(ex.message)
        return ResponseEntity
            .status(ErrorCode.FORBIDDEN.httpStatus)
            .body(ErrorResponse.of(ErrorCode.FORBIDDEN, ex.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Any> {
        log.error(ex.message)
        return ResponseEntity
            .status(ErrorCode.INTERNAL_SERVER_ERROR.httpStatus)
            .body(ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.message))
    }
}