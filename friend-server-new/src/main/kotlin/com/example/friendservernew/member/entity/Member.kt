package com.example.friendservernew.member.entity

import com.example.friendservernew.common.enitiy.BaseModifiableEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class Member(
    nickname: String,
    email: String,
    password: String
) : BaseModifiableEntity() {
    @Column(nullable = false)
    var nickname: String = nickname
        protected set

    @Column(unique = true, nullable = false)
    val email: String = email

    @Column(nullable = false)
    var password: String = password
        protected set

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableAuthorities: MutableList<Authority> = mutableListOf()
    // 외부에 노출하는 collection 은 immutable 하도록 변경
    val authorities: List<Authority> get() = mutableAuthorities.toList()

    companion object {
        fun createUser(
            nickname: String,
            email: String,
            password: String,
        ): Member {
            val member = Member(nickname = nickname, email = email, password = password)
            member.addAuthority(Authority.Role.ROLE_USER)
            return member
        }

        fun createAdmin(
            nickname: String,
            email: String,
            password: String,
        ): Member {
            val member = Member(nickname = nickname, email = email, password = password)
            member.addAuthority(Authority.Role.ROLE_ADMIN)
            return member
        }
    }

    // 어플리케이션에서 member를 생성자로 생성하지 못하도록 방지
    protected constructor() : this("", "", "")

    fun addAuthority(role: Authority.Role) {
        mutableAuthorities.add(Authority(role = role, member = this))
    }

    fun changeNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changePassword(password: String) {
        this.password = password
    }
}
