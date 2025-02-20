package com.example.user.infrastructure.util

import com.example.user.domain.auth.JwtClaimReader
import com.example.user.domain.valueobject.type.AuthorityRoleType
import com.example.user.domain.valueobject.JwtKey
import com.example.user.domain.valueobject.type.JwtType
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class JwtClaimReaderImpl(
    private val jwtUtil: JwtUtil
) : JwtClaimReader {
    override fun getEmail(token: String) : String? {
        return jwtUtil.getClaim(token, JwtKey.EMAIL.value, String::class.java)
    }

    override fun getMemberId(token: String): UUID? {
        val memberIdStr = jwtUtil.getClaim(token, JwtKey.MEMBER_ID.value, String::class.java)
            ?: return null
        return try {
            UUID.fromString(memberIdStr)
        } catch (e: Exception) {
            return null
        }
    }

    override fun getAuthorities(token: String): List<AuthorityRoleType>? {
        val authorities = jwtUtil.getClaim(token, JwtKey.AUTHORITIES.value, List::class.javaObjectType)?.filterIsInstance<String>()
            ?: return null
        return try {
            authorities.map { AuthorityRoleType.valueOf(it) }
        } catch (e: Exception) {
            return null
        }
    }

    override fun getType(token: String): JwtType? {
        val typeStr = jwtUtil.getClaim(token, JwtKey.TYPE.value, String::class.java)
            ?: return null
        return try {
            JwtType.valueOf(typeStr)
        } catch (e: Exception) {
            return null
        }
    }
}