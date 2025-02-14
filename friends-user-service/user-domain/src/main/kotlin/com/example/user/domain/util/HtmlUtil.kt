package com.example.user.domain.util

interface HtmlUtil {
    fun createHtmlFromTemplate(
        templatePath: String,
        vararg params: Pair<String, Any>,
    ) : String
}