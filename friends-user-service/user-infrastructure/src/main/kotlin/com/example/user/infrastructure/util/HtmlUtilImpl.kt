package com.example.user.infrastructure.util

import com.example.user.domain.util.HtmlUtil
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Component
class HtmlUtilImpl(
    private val templateEngine: TemplateEngine
) : HtmlUtil{

    override fun createHtmlFromTemplate(templatePath: String, vararg params: Pair<String, Any>): String {
        val context = Context()
        params.forEach { (key, value) -> context.setVariable(key, value) }
        return templateEngine.process(templatePath, context)
    }
}