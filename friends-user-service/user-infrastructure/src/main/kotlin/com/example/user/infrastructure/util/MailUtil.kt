package com.example.user.infrastructure.util

import jakarta.mail.Message
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailUtil(
    private val mailSender: JavaMailSender,
)  {
    fun sendHtml(
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