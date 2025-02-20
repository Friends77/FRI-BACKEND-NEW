package com.example.auth.application.service

import com.example.auth.application.dto.ChangeBirthDto
import com.example.auth.application.dto.ChangeCategoriesDto
import com.example.auth.application.dto.ChangeGenderDto
import com.example.auth.application.dto.ChangeLocationDto
import com.example.auth.application.dto.ChangeMbtiDto
import com.example.auth.application.dto.ChangeNicknameDto
import com.example.auth.application.dto.ChangeProfileImageDto
import com.example.auth.application.dto.ChangeSelfDescriptionDto
import com.example.user.domain.event.EventPublisher
import com.example.user.domain.event.ProfileImageUrlChangedEvent
import com.example.user.domain.event.ProfileNicknameChangedEvent
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.service.ProfileChangeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional
@Service
class ProfileChangeUseCase (
    private val profileRepository: ProfileRepository,
    private val profileChangeService: ProfileChangeService,
    private val eventPublisher: EventPublisher
){
    fun changeNickname(changeNicknameDto: ChangeNicknameDto) {
        val memberId = changeNicknameDto.memberId
        val nickname = changeNicknameDto.nickname
        profileChangeService.changeNickname(memberId, nickname)

        val event = ProfileNicknameChangedEvent(memberId, nickname)
        eventPublisher.publishProfileNicknameChangedEvent(event)
    }

    fun changeBirth(changeBirthDto: ChangeBirthDto) {
        val memberId = changeBirthDto.memberId
        val localDate = changeBirthDto.localDate
        profileChangeService.changeBirth(memberId, localDate)
    }

    fun changeGender(changeGenderDto: ChangeGenderDto) {
        val memberId = changeGenderDto.memberId
        val genderStr = changeGenderDto.gender
        profileChangeService.changeGender(memberId, genderStr)
    }

    fun changeLocation(changeLocationDto: ChangeLocationDto) {
        val memberId = changeLocationDto.memberId
        val latitude = changeLocationDto.latitude
        val longitude = changeLocationDto.longitude
        profileChangeService.changeLocation(memberId, latitude, longitude)
    }

    fun changeMbti(changeMbtiDto: ChangeMbtiDto) {
        val memberId = changeMbtiDto.memberId
        val mbtiStr = changeMbtiDto.mbti
        profileChangeService.changeMbti(memberId, mbtiStr)
    }

    fun changeProfileImage(changeProfileImageDto: ChangeProfileImageDto) {
        val memberId = changeProfileImageDto.memberId
        val imageUrl = changeProfileImageDto.imageUrl
        profileChangeService.changeProfileImage(memberId, imageUrl)

        val event = ProfileImageUrlChangedEvent(memberId, imageUrl)
        eventPublisher.publishProfileImageUrlChangedEvent(event)
    }

    fun changeCategories(changeCategoriesDto: ChangeCategoriesDto) {
        val memberId = changeCategoriesDto.memberId
        val categories = changeCategoriesDto.categories
        profileChangeService.changeCategories(memberId, categories)
    }

    fun changeSelfDescription(changeSelfDescriptionDto: ChangeSelfDescriptionDto) {
        val memberId = changeSelfDescriptionDto.memberId
        val selfDescription = changeSelfDescriptionDto.selfDescription
        profileChangeService.changeSelfDescription(memberId, selfDescription)
    }
}