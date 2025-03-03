package com.example.user.domain.entity

import com.example.user.domain.entity.base.BaseModifiableEntity
import com.example.user.domain.exception.OAuth2UserPasswordChangeException
import com.example.user.domain.valueobject.type.AuthorityRoleType
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.EncodedPassword
import com.example.user.domain.valueobject.OAuth2Provider
import com.example.user.domain.valueobject.attributeconverter.EmailConverter
import com.example.user.domain.valueobject.attributeconverter.EncodedPasswordConverter
import com.example.user.domain.valueobject.attributeconverter.OAuth2ProviderConverter
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class Member private constructor(
    email: Email,
    isOAuth2User: Boolean,
    oAuth2Provider: OAuth2Provider? = null
) : BaseModifiableEntity() {
    @Column(nullable = false, unique = true)
    @Convert(converter = EmailConverter::class)
    val email: Email = email

    @Column(name = "password", nullable = true)
    @Convert(converter = EncodedPasswordConverter::class)
    var password: EncodedPassword? = null
        protected set

    @Column(name = "oauth2_provider", nullable = true)
    @Convert(converter = OAuth2ProviderConverter::class)
    val oAuth2Provider : OAuth2Provider? = null

    @Column(name = "is_oauth2_user", nullable = false)
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
            member.addAuthority(AuthorityRoleType.ROLE_USER)
            return member
        }

        fun createUserByOAuth2(
            email: Email,
            oAuth2Provider: OAuth2Provider
        ): Member {
            val member = Member(email = email, isOAuth2User = true, oAuth2Provider = oAuth2Provider)
            member.addAuthority(AuthorityRoleType.ROLE_USER)
            return member
        }


        fun createAdmin(
            email: Email,
            password: EncodedPassword,
        ): Member {
            val member = Member(email = email, isOAuth2User = false)
            member.password = password
            member.addAuthority(AuthorityRoleType.ROLE_ADMIN)
            return member
        }
    }

    private fun addAuthority(role: AuthorityRoleType) {
        mutableAuthorities.add(Authority(role = role, member = this))
    }

    fun changePassword(password: EncodedPassword) {
        if (isOAuth2User || this.password == null) {
            throw OAuth2UserPasswordChangeException()
        }
        this.password = password
    }

}