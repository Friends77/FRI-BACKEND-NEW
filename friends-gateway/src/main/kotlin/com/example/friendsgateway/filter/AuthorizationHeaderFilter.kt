package com.example.friendsgateway.filter

import com.example.friendsgateway.util.JwtUtil
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus

abstract class AuthorizationHeaderFilter(
    private val jwtUtil: JwtUtil,
    private val authorities : List<Authority>
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    val logger = LoggerFactory.getLogger(AuthorizationHeaderFilter::class.java)

    data class Config (
        val headerName: String,
        val jwtTypeParameter : String,
        val authorityParameter : String
    )

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            try {
                val method = exchange.request.method.name()
                val path = exchange.request.path.toString()
                logger.info("Incoming Request: $method $path")

                val accessToken = exchange.request.headers.getFirst(config.headerName)?.substring(7)
                    ?: throw Exception("No Authorization Header")
                val jwtTypeStr = jwtUtil.getClaim(accessToken, config.jwtTypeParameter, String::class.java)
                    ?: throw Exception("No JWT Type Parameter")
                val jwtType = JwtType.valueOf(jwtTypeStr)

                // 인증 검사
                if (!jwtUtil.isValid(accessToken) || jwtType != JwtType.ACCESS) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    return@GatewayFilter exchange.response.setComplete()
                }
                // 인가 검사
                val authoritiesInParam = jwtUtil.getClaim(accessToken, config.authorityParameter, List::class.javaObjectType)?.filterIsInstance<String>()
                    ?: throw Exception("No Authorities")
                if (!authoritiesInParam.containsAll(authorities.map { it.name })) {
                    exchange.response.statusCode = HttpStatus.FORBIDDEN
                    return@GatewayFilter exchange.response.setComplete()
                }

                return@GatewayFilter chain.filter(exchange)
            } catch (e: Exception) {
                logger.info("Error: ${e.message}")
                exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                return@GatewayFilter exchange.response.setComplete()
            }
        }

    }
}