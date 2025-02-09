package com.example.friendservernew.auth.service.authmail

import com.example.friendservernew.auth.repository.MailVerificationCodeRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.util.concurrent.ExecutorService
import com.example.friendservernew.common.util.mail.MailUtil
import com.example.friendservernew.auth.exception.EmailAuthenticationFailedException

@Service
class AuthMailSendService(
    private val mailUtil: MailUtil,
    private val templateEngine: TemplateEngine,
    private val executorService: ExecutorService, // VirtualThreadExecutor
    private val mailVerificationCodeRepository: MailVerificationCodeRepository,
) {
    private val logger = LoggerFactory.getLogger(AuthMailSendService::class.java)

    companion object {
        private const val EMAIL_VERIFY_TEMPLATE_PATH = "email-verify-template"
        private const val EMAIL_VERIFY_SUBJECT = "[Atelier] 이메일 인증 코드를 확인해주세요"
        private const val TEAM_NAME = "Atelier"
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
        val savedCode = mailVerificationCodeRepository.getCode(email) ?: throw EmailAuthenticationFailedException()
        if (savedCode == code) {
            mailVerificationCodeRepository.deleteCode(email)
        } else {
            throw EmailAuthenticationFailedException()
        }
    }

    private fun sendVerificationMail(to: String) {
        val code = createVerifyCode()
        mailVerificationCodeRepository.save(email = to, code = code)
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
