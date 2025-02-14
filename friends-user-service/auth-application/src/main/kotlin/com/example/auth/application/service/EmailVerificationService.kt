package com.example.auth.application.service

import com.example.auth.application.exception.EmailAuthenticationFailedException
import com.example.auth.application.util.mail.MailUtil
import com.example.user.domain.repository.EmailVerificationCodeRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.util.concurrent.ExecutorService

@Service
class EmailVerificationService(
    private val mailUtil: MailUtil,
    private val templateEngine: TemplateEngine,
    private val executorService: ExecutorService, // VirtualThreadExecutor
    private val emailVerificationCodeRepository: EmailVerificationCodeRepository
) {
    private val logger = LoggerFactory.getLogger(EmailVerificationService::class.java)

    companion object {
        private const val EMAIL_VERIFY_TEMPLATE_PATH = "email-verify-template"
        private const val EMAIL_VERIFY_SUBJECT = "[Friends] 이메일 인증 코드를 확인해주세요"
        private const val TEAM_NAME = "Friends"
    }

    fun sendVerificationMailAsync(to: String) {
        executorService.submit {
            try {
                sendVerificationMail(to)
            } catch (e: Exception) {
                logger.error("$to : 이메일 인증 코드 전송 실패", e)
            }
        }
    }

    fun verifyEmailCode(
        email: String,
        code: String,
    ) {
        val savedCode = emailVerificationCodeRepository.getCode(email) ?: throw EmailAuthenticationFailedException()
        if (savedCode == code) {
            emailVerificationCodeRepository.deleteCode(email)
        } else {
            throw EmailAuthenticationFailedException()
        }
    }

    private fun sendVerificationMail(to: String) {
        val code = createVerifyCode()
        emailVerificationCodeRepository.saveCode(email = to, code = code)
        val html = createMailHtml(code = code, team = TEAM_NAME)
        mailUtil.sendHtml(to = to, subject = EMAIL_VERIFY_SUBJECT, html = html)
    }

    private fun createMailHtml(
        code: String,
        team: String,
    ): String {
        val context = Context()
        context.setVariable("code", code)
        context.setVariable("team", team)
        return templateEngine.process(EMAIL_VERIFY_TEMPLATE_PATH, context)
    }

    private fun createVerifyCode(): String = (100000..999999).random().toString()
}
