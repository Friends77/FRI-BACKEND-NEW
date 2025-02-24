package com.example.user.boot


import com.example.user.domain.auth.PasswordEncoder
import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.EncodedPassword
import com.example.user.domain.valueobject.Nickname
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class InitDB(
    private val initTestMember: InitTestMember,
) {
    @PostConstruct
    fun init() {
        initTestMember.init()
    }

    @Component
    class InitTestMember(
        private val memberRepository: MemberRepository,
        private val profileRepository: ProfileRepository,
        private val passwordEncoder: PasswordEncoder
    ) {
        fun init() {
            val member1 = Member.createUser(
                Email("test1@test.com"),
                EncodedPassword(passwordEncoder.encode("1234"))
            )
            memberRepository.save(member1)
            profileRepository.save(Profile(member1.id, Nickname("test1")))

            val member2 = Member.createUser(
                Email("test2@test.com"),
                EncodedPassword(passwordEncoder.encode("1234"))
            )
            memberRepository.save(member2)
            profileRepository.save(Profile(member2.id, Nickname("test2")))
        }
    }
}