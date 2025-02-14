package com.example.user.domain.util

interface MailUtil {
    fun sendHtml(
        to: String,
        subject: String,
        html: String,
    )
}