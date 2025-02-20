package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.valueobject.AuthorityRole
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.EncodedPassword
import com.example.user.domain.valueobject.OAuth2Provider
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.OneToMany

@Entity
class Member private constructor(
    email: Email,
    isOAuth2User: Boolean
) : BaseModifiableEntity() {
    @Embedded
    val email: Email = email

    @Embedded
    var password: EncodedPassword? = null
        protected set

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var oAuth2Provider: OAuth2Provider? = null
        protected set

    @Column(nullable = false)
    val isOAuth2User : Boolean = isOAuth2User

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableAuthorities: MutableList<Authority> = mutableListOf()
    // 외부에 노출하는 collection 은 immutable 하도록 변경
    val authorities: List<Authority> get() = mutableAuthorities.toList()

    companion object {
        fun createUser(
            email: Email,
            password: EncodedPassword,
        ): Member {
            val member = Member(email = email, isOAuth2User = false)
            member.password = password
            member.addAuthority(AuthorityRole.ROLE_USER)
            return member
        }

        fun createUserByOAuth2(
            email: Email,
            oAuth2Provider: OAuth2Provider,
        ): Member {
            val member = Member(email = email, isOAuth2User = true)
            member.oAuth2Provider = oAuth2Provider
            member.addAuthority(AuthorityRole.ROLE_USER)
            return member
        }


        fun createAdmin(
            email: Email,
            password: EncodedPassword,
        ): Member {
            val member = Member(email = email, isOAuth2User = false)
            member.password = password
            member.addAuthority(AuthorityRole.ROLE_ADMIN)
            return member
        }
    }

    private fun addAuthority(role: AuthorityRole) {
        mutableAuthorities.add(Authority(role = role, member = this))
    }

    fun changePassword(password: EncodedPassword) {
        this.password = password
    }
}