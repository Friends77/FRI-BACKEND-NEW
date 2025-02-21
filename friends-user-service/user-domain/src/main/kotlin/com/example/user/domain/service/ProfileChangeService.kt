package com.example.user.domain.service

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
import java.time.LocalDate
import java.util.UUID

@Service
class ProfileChangeService (
    private val profileRepository: ProfileRepository,
){
    // TODO : 추천 시스템에 프로필 변경 이벤트 전달

    fun changeNickname(memberId : UUID, nickname : String){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeNickname(Nickname(nickname))
    }

    fun changeBirth(memberId : UUID, localDate: LocalDate){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeBirth(Birth(localDate))
    }

    fun changeGender(memberId : UUID, genderStr : String) {
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeGender(Gender(genderStr))
    }

    fun changeLocation(memberId: UUID, latitude: Double, longitude: Double){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeLocation(Location(latitude, longitude))
    }

    fun changeMbti(memberId: UUID, mbtiStr: String){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeMbti(MBTI(mbtiStr))
    }

    fun changeSelfDescription(memberId: UUID, selfDescription: String){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeSelfDescription(SelfDescription(selfDescription))
    }

    fun changeProfileImage(memberId: UUID, imageUrl: String?){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        val image = imageUrl?.let { Image(it) }
        profile.changeImageUrl(image)
    }

    fun changeCategories(memberId: UUID, categoryList : List<String>){
        if (categoryList.size > 5) {
            throw IllegalProfileArgumentException("카테고리는 5개 이하로 선택해주세요.")
        }
        val categories = categoryList.map { Category(it) }
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeCategories(categories)
    }
}