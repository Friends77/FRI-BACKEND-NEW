package com.example.user.domain.common

import com.github.f4b6a3.ulid.UlidCreator
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import jakarta.persistence.Transient
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.util.Objects
import java.util.UUID

@MappedSuperclass
abstract class PrimaryKeyEntity : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = UlidCreator.getMonotonicUlid().toUuid()

    @Transient // 데이터베이스에 저장되지 않는 필드
    private var _isNew = true

    override fun getId(): UUID = id

    // jpa save 메서드 호출 시 isNew() 메서드를 호출하여 새로운 엔티티인지 확인
    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        /**
         * 논리적으로 같은 앤티티 더라도 프록시일 경우 클래스가 다르므로 예외 상황을 처리합니다.
         */
        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Any? =
        if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as PrimaryKeyEntity).id
        }

    override fun hashCode() = Objects.hashCode(id)

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }
}