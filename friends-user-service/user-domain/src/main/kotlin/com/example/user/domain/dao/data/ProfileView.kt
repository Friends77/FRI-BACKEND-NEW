package com.example.user.domain.dao.data

import com.example.user.domain.dao.converter.StringListConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Subselect
import java.time.LocalDate
import java.util.UUID

@Entity
@Immutable
@Subselect(
    """
        SELECT 
            p.member_id AS member_id,
            p.id AS profile_id,
            p.nickname AS nickname,
            p.birth AS birth,
            p.gender AS gender,
            p.mbti AS mbti,
            p.image_url AS image_url,
            p.self_description AS self_description,
            p.latitude AS latitude,
            p.longitude AS longitude,
            json_agg(pc.category) AS categories
        FROM profile p
        LEFT JOIN profile_category pc ON p.id = pc.profile_id
        GROUP BY p.id
    """
)
class ProfileView(
    @Id
    @Column(name = "profile_id")
    val profileId: UUID,

    @Column(name = "member_id")
    val memberId: UUID,

    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "birth")
    val birth: LocalDate?,

    @Column(name = "gender")
    val gender: String?,

    @Column(name = "mbti")
    val mbti: String?,

    @Column(name = "image_url")
    val imageUrl: String?,

    @Column(name = "self_description")
    val selfDescription: String?,

    @Column(name = "latitude")
    val latitude: Double?,

    @Column(name = "longitude")
    val longitude: Double?,

    @Column(name = "categories")
    @Convert(converter = StringListConverter::class)
    val categories: List<String>
) {
}