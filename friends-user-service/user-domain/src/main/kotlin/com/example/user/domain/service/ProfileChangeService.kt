package com.example.user.domain.service

import com.example.user.domain.exception.InvalidProfilePropertyException
import com.example.user.domain.exception.ProfileNotFoundByMemberIdException
import com.example.user.domain.repository.ProfileRepository
import com.example.user.domain.validator.UserRegisterValidator
import com.example.user.domain.valueobject.Birth
import com.example.user.domain.valueobject.type.CategorySubType
import com.example.user.domain.valueobject.type.GenderType
import com.example.user.domain.valueobject.Location
import com.example.user.domain.valueobject.type.MBTIType
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

@Service
class ProfileChangeService (
    private val profileRepository: ProfileRepository,
    private val userRegisterValidator: UserRegisterValidator,
){
    // TODO : 추천 시스템에 프로필 변경 이벤트 전달

    fun changeNickname(memberId : UUID, nickname : String){
        userRegisterValidator.validateNicknamePattern(nickname)
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeNickname(nickname)
    }

    fun changeBirth(memberId : UUID, localDate: LocalDate){
        val birth = Birth.of(localDate)
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeBirth(birth)
    }

    fun changeGender(memberId : UUID, genderStr : String) {
        val genderType = try {
            GenderType.valueOf(genderStr)
        } catch (e: Exception) {
            throw InvalidProfilePropertyException("존재하지 않는 성별입니다.")
        }
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeGender(genderType)
    }

    fun changeLocation(memberId: UUID, latitude: Double, longitude: Double){
        val location = Location.of(latitude, longitude)
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeLocation(location)
    }

    fun changeMbti(memberId: UUID, mbtiStr: String){
        val mbtiType = try {
            MBTIType.valueOf(mbtiStr)
        } catch (e: Exception) {
            throw InvalidProfilePropertyException("존재하지 않는 MBTI입니다.")
        }
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeMbti(mbtiType)
    }

    fun changeSelfDescription(memberId: UUID, selfDescription: String){
        if (selfDescription.length > 100) {
            throw InvalidProfilePropertyException("짧은 자기 소개는 100자 이하로 입력해주세요.")
        }
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeSelfDescription(selfDescription)
    }

    fun changeProfileImage(memberId: UUID, imageUrl: String?){
        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeImageUrl(imageUrl)
    }

    fun changeCategories(memberId: UUID, categoryList : List<String>){
        if (categoryList.size > 5) {
            throw InvalidProfilePropertyException("카테고리는 5개 이하로 선택해주세요.")
        }
        val categories : MutableList<CategorySubType> = mutableListOf()
        for (category in categoryList){
            val categorySubTypeEnum = try {
                CategorySubType.valueOf(category)
            } catch (e: Exception) {
                throw InvalidProfilePropertyException("[${category}] 는 존재하지 않는 카테고리입니다.")
            }
            categories.add(categorySubTypeEnum)
        }

        val profile = profileRepository.findByMemberId(memberId) ?: throw ProfileNotFoundByMemberIdException()
        profile.changeCategories(categories)
    }
}