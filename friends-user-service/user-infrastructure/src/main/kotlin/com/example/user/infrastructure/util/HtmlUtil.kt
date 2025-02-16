package com.example.user.infrastructure.util

import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Component
class HtmlUtil(
    private val templateEngine: TemplateEngine
)  {
    fun createHtmlFromTemplate(templatePath: String, vararg params: Pair<String, Any>): String {
        val context = Context()
        params.forEach { (key, value) -> context.setVariable(key, value) }
        return templateEngine.process(templatePath, context)
    }
}