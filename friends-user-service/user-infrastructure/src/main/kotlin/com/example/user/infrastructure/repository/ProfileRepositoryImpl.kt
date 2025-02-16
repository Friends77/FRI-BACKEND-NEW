package com.example.user.infrastructure.repository

import com.example.user.domain.entity.Profile
import com.example.user.domain.repository.ProfileRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProfileRepositoryImpl : JpaRepository<Profile, UUID>, ProfileRepository {

}