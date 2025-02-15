package com.example.user.boot.exception

import com.example.auth.application.exception.AuthBaseException
import com.example.auth.application.exception.AuthErrorResponse
import com.example.user.application.exception.UserBaseException
import com.example.user.application.exception.UserErrorResponse
import com.example.user.domain.exception.BaseException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BaseException::class)
    fun handleCustomException(ex: BaseException): ResponseEntity<Any> {
        log.info(ex.message)
        return ResponseEntity
            .status(ex.errorCode.httpStatus)
            .body(UserErrorResponse.of(ex.errorCode, ex.message))
    }

    @ExceptionHandler(AuthBaseException::class)
    fun handleAuthBaseException(ex: AuthBaseException): ResponseEntity<Any> {
        log.info(ex.message)
        return ResponseEntity
            .status(ex.errorCode.httpStatus)
            .body(AuthErrorResponse.of(ex.errorCode, ex.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<Any> {
        log.error(ex.message)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("서버 내부 오류입니다.")
    }
}