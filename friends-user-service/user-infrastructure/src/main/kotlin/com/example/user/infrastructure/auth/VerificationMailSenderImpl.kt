package com.example.user.infrastructure.auth

import com.example.user.domain.exception.EmailSendFailedException
import com.example.user.domain.auth.VerificationMailSender
import com.example.user.infrastructure.repository.EmailVerificationCodeRepositoryImpl
import com.example.user.infrastructure.util.HtmlUtil
import com.example.user.infrastructure.util.MailUtil
import org.springframework.stereotype.Component

@Component
class VerificationMailSenderImpl (
    private val mailUtil: MailUtil,
    private val htmlUtil: HtmlUtil,
    private val emailVerificationCodeRepositoryImpl: EmailVerificationCodeRepositoryImpl,
) : VerificationMailSender {
    companion object {
        private const val EMAIL_VERIFY_TEMPLATE_PATH = "email-verify-template"
        private const val EMAIL_VERIFY_SUBJECT = "[Friends] 이메일 인증 코드를 확인해주세요"
        private const val TEAM_NAME = "Friends"
    }

    override fun sendVerificationMail(to: String) {
        try {
            val code = createVerifyCode()
            emailVerificationCodeRepositoryImpl.saveCode(email = to, code = code)
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