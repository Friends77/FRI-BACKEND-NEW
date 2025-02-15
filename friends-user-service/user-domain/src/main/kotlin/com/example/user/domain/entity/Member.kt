package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.valueobject.AuthorityRole
import com.example.user.domain.valueobject.OAuth2Provider
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.OneToMany

@Entity
class Member private constructor(
    email: String,
) : BaseModifiableEntity() {
    @Column(unique = true, nullable = false)
    val email: String = email

    @Column(nullable = true)
    var password: String? = null
        protected set

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    var oAuth2Provider: OAuth2Provider? = null
        protected set


    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableAuthorities: MutableList<Authority> = mutableListOf()
    // 외부에 노출하는 collection 은 immutable 하도록 변경
    val authorities: List<Authority> get() = mutableAuthorities.toList()

    companion object {
        fun createUser(
            email: String,
            password: String,
        ): Member {
            val member = Member(email = email)
            member.password = password
            member.addAuthority(AuthorityRole.ROLE_USER)
            return member
        }

        fun createUserByOAuth2(
            email: String,
            oAuth2Provider: OAuth2Provider,
        ): Member {
            val member = Member(email = email)
            member.oAuth2Provider = oAuth2Provider
            member.addAuthority(AuthorityRole.ROLE_USER)
            return member
        }


        fun createAdmin(
            email: String,
            password: String,
        ): Member {
            val member = Member(email = email)
            member.password = password
            member.addAuthority(AuthorityRole.ROLE_ADMIN)
            return member
        }
    }

    private fun addAuthority(role: AuthorityRole) {
        mutableAuthorities.add(Authority(role = role, member = this))
    }

    fun changePassword(password: String) {
        this.password = password
    }
}