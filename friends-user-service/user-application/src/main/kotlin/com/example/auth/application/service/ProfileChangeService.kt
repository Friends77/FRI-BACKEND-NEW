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
import com.example.user.domain.exception.IllegalProfileArgumentException
import com.example.user.domain.exception.ProfileNotFoundByMemberIdException
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.valueobject.Birth
import com.example.user.domain.valueobject.Category
import com.example.user.domain.valueobject.Gender
import com.example.user.domain.valueobject.Image
import com.example.user.domain.valueobject.Location
import com.example.user.domain.valueobject.MBTI
import com.example.user.domain.valueobject.Nickname
import com.example.user.domain.valueobject.SelfDescription
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProfileChangeService (
    private val profileRepository: ProfileRepository,
    private val eventPublisher: EventPublisher
){
    // TODO : 추천 시스템에 프로필 변경 이벤트 전달

    fun changeNickname(changeNicknameDto: ChangeNicknameDto){
        val memberId = changeNicknameDto.memberId
        val nickname = changeNicknameDto.nickname

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeNickname(Nickname(nickname))

        val event = ProfileNicknameChangedEvent(memberId, profile.nickname.value)
        eventPublisher.publishProfileNicknameChangedEvent(event)
    }

    fun changeBirth(changeBirthDto: ChangeBirthDto){
        val memberId = changeBirthDto.memberId
        val localDate = changeBirthDto.localDate

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeBirth(Birth(localDate))
    }

    fun changeGender(changeGenderDto: ChangeGenderDto) {
        val memberId = changeGenderDto.memberId
        val genderStr = changeGenderDto.gender

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeGender(Gender(genderStr))
    }

    fun changeLocation(changeLocationDto: ChangeLocationDto){
        val memberId = changeLocationDto.memberId
        val latitude = changeLocationDto.latitude
        val longitude = changeLocationDto.longitude

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeLocation(Location(latitude, longitude))
    }

    fun changeMbti(changeMbtiDto: ChangeMbtiDto){
        val memberId = changeMbtiDto.memberId
        val mbtiStr = changeMbtiDto.mbti

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeMbti(MBTI(mbtiStr))
    }

    fun changeSelfDescription(changeSelfDescriptionDto: ChangeSelfDescriptionDto){
        val memberId = changeSelfDescriptionDto.memberId
        val selfDescription = changeSelfDescriptionDto.selfDescription

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeSelfDescription(SelfDescription(selfDescription))
    }

    fun changeProfileImage(changeProfileImageDto: ChangeProfileImageDto){
        val memberId = changeProfileImageDto.memberId
        val imageUrl = changeProfileImageDto.imageUrl

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        val image = imageUrl?.let { Image(it) }
        profile.changeImageUrl(image)

        val event = ProfileImageUrlChangedEvent(memberId, profile.image?.url)
        eventPublisher.publishProfileImageUrlChangedEvent(event)
    }

    fun changeCategories(changeCategoriesDto: ChangeCategoriesDto){
        val memberId = changeCategoriesDto.memberId
        val categoryList = changeCategoriesDto.categories

        val categories = categoryList.map { Category(it) }
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeCategories(categories)
    }
}