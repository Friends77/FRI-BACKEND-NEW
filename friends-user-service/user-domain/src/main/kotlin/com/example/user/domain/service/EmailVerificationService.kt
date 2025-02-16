package com.example.user.domain.service

import com.example.user.domain.exception.EmailSendFailedException
import com.example.user.domain.exception.InvalidEmailVerificationCodeException
import com.example.user.domain.repository.EmailVerificationCodeRepository
import com.example.user.domain.util.HtmlUtil
import com.example.user.domain.util.MailUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmailVerificationService(
    private val mailUtil: MailUtil,
    private val htmlUtil: HtmlUtil,
    private val emailVerificationCodeRepository: EmailVerificationCodeRepository
) {
    private val logger = LoggerFactory.getLogger(EmailVerificationService::class.java)

    companion object {
        private const val EMAIL_VERIFY_TEMPLATE_PATH = "email-verify-template"
        private const val EMAIL_VERIFY_SUBJECT = "[Friends] 이메일 인증 코드를 확인해주세요"
        private const val TEAM_NAME = "Friends"
    }

    fun sendVerificationMail(to: String) {
        try {
            val code = createVerifyCode()
            emailVerificationCodeRepository.saveCode(email = to, code = code)
            val html = createMailHtml(code = code, team = TEAM_NAME)
            mailUtil.sendHtml(to = to, subject = EMAIL_VERIFY_SUBJECT, html = html)
        } catch (e: Exception) {
            throw EmailSendFailedException()
        }

    }

    private fun createMailHtml(
        code: String,
        team: String,
    ): String {
        return htmlUtil.createHtmlFromTemplate(
            templatePath = EMAIL_VERIFY_TEMPLATE_PATH,
            "code" to code, "team" to team
        )
    }

    private fun createVerifyCode(): String = (100000..999999).random().toString()
}
