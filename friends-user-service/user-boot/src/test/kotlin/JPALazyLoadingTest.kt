import com.example.user.boot.UserApplication
import com.example.user.domain.entity.Member
import com.example.user.domain.entity.Profile
import com.example.user.domain.repository.MemberRepository
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.valueobject.Email
import com.example.user.domain.valueobject.EncodedPassword
import com.example.user.domain.valueobject.Nickname
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions
import org.hibernate.Hibernate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [UserApplication::class])
@Transactional
class JPALazyLoadingTest(
    @Autowired private val memberRepository: MemberRepository,
    @Autowired private val profileRepository: ProfileRepository,
    @Autowired private val entityManager: EntityManager
) {
    @Test
    fun lazyLoadingTest() {
        val member = Member.createUser(Email("test@test.com"), EncodedPassword("password"))
        val profile = Profile(member, Nickname("test"))
        memberRepository.save(member)
        profileRepository.save(profile)

        entityManager.clear()
        entityManager.flush()


        val findProfile = profileRepository.findByMemberId(member.id)
        // member 가 프록시 객체인지 검증
        Assertions.assertThat(Hibernate.isInitialized(findProfile!!.member)).isTrue()
    }
}