package com.example.user.infrastructure.util

import com.example.user.domain.util.MailUtil
import jakarta.mail.Message
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailUtilImpl(
    private val mailSender: JavaMailSender,
) : MailUtil{
    override fun sendHtml(
        to: String,
        subject: String,
        html: String,
    ) {
        val message = mailSender.createMimeMessage()
        message.subject = subject
        message.setText(html, "utf-8", "html")
        message.setRecipients(
            Message.RecipientType.TO,
            to,
        )
        mailSender.send(message)
    }
}