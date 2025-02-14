package com.example.auth.application.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class MailConfig(
    @Value("\${spring.mail.host}") private val host: String,
    @Value("\${spring.mail.port}") private val port: Int,
    @Value("\${spring.mail.username}") private val username: String,
    @Value("\${spring.mail.password}") private val password: String,
    @Value("\${spring.mail.auth}") private val auth: Boolean,
    @Value("\${spring.mail.starttls}") private val starttls: Boolean,
    @Value("\${spring.mail.debug}") private val debug: Boolean,
    @Value("\${spring.mail.connectiontimeout}") private val connectiontimeout: Int,
) {
    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port

        mailSender.username = username
        mailSender.password = password

        val props = mailSender.javaMailProperties
        props["mail.smtp.auth"] = auth
        props["mail.smtp.starttls.enable"] = starttls
        props["mail.debug"] = debug
        props["mail.smtp.connectiontimeout"] = connectiontimeout

        return mailSender
    }
}
