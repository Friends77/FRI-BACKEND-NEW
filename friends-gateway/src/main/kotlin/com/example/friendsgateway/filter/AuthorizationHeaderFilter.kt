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

                // 인증 검사
                val accessToken = exchange.request.headers.getFirst(config.headerName)?.substring(7)
                if (accessToken == null) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    throw Exception("No Authorization Header")
                }
                // JWT 를 파싱할 수 있는지, 유효한지 검사
                if (!jwtUtil.isValid(accessToken)) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    throw Exception("Invalid JWT")
                }

                // JWT 타입 파싱 및 검사
                val jwtTypeStr = jwtUtil.getClaim(accessToken, config.jwtTypeParameter, String::class.java)
                if (jwtTypeStr == null) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    throw Exception("No JWT Type")
                }

                // JWT 타입이 ACCESS 인지 검사
                if (!JwtType.entries.map { it.name }.contains(jwtTypeStr)) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    throw Exception("Invalid JWT Type")
                }
                val jwtType = JwtType.valueOf(jwtTypeStr)
                if (jwtType != JwtType.ACCESS) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    throw Exception("Invalid JWT Type")
                }

                // 인가 검사
                val authoritiesInParam = jwtUtil.getClaim(accessToken, config.authorityParameter, List::class.javaObjectType)?.filterIsInstance<String>()
                if (authoritiesInParam == null) {
                    exchange.response.statusCode = HttpStatus.FORBIDDEN
                    throw Exception("No Authorities in JWT")
                }
                if (!authoritiesInParam.containsAll(authorities.map { it.name })) {
                    exchange.response.statusCode = HttpStatus.FORBIDDEN
                    throw Exception("Insufficient Authorities")
                }

                // accessToken 에서 memberId 추출
                val memberId = jwtUtil.getClaim(accessToken, "memberId", String::class.java)
                if (memberId == null) {
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    throw Exception("JWT에 memberId 클레임이 없습니다.")
                }

                val mutatedRequest = exchange.request.mutate()
                    .header("X-Member-Id", memberId) // 사용자 정의 헤더 이름
                    .build()

                val mutatedExchange = exchange.mutate().request(mutatedRequest).build()

                return@GatewayFilter chain.filter(mutatedExchange)
            } catch (e: Exception) {
                logger.info("Error: ${e.message}")
                return@GatewayFilter exchange.response.setComplete()
            }
        }
    }
}