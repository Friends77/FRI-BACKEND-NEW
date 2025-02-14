package com.example.user.domain.repository

import com.example.user.domain.entity.Profile

interface ProfileRepository {
    fun save(profile: Profile): Profile
}