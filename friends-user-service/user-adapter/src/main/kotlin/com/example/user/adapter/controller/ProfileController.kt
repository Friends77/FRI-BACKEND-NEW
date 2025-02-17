package com.example.user.adapter.controller

import com.example.auth.application.dto.ChangeBirthDto
import com.example.auth.application.dto.ChangeCategoriesDto
import com.example.auth.application.dto.ChangeGenderDto
import com.example.auth.application.dto.ChangeLocationDto
import com.example.auth.application.dto.ChangeMbtiDto
import com.example.auth.application.dto.ChangeNicknameDto
import com.example.auth.application.dto.ChangeProfileImageDto
import com.example.auth.application.dto.ProfileDto
import com.example.auth.application.service.ProfileChangeUseCase
import com.example.auth.application.service.ProfileQueryUseCase
import com.example.user.adapter.ChangeBirthRequestDto
import com.example.user.adapter.ChangeCategoriesRequestDto
import com.example.user.adapter.ChangeGenderRequestDto
import com.example.user.adapter.ChangeLocationRequestDto
import com.example.user.adapter.ChangeMbtiRequestDto
import com.example.user.adapter.ChangeNicknameRequestDto
import com.example.user.adapter.ChangeProfileImageRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/user/profile")
class ProfileController (
    private val profileChangeUseCase: ProfileChangeUseCase,
    private val profileQueryUseCase: ProfileQueryUseCase
){
    @GetMapping
    fun getMyProfile(
        @RequestHeader("X-Member-Id") memberIdInHeader : String
    ) : ResponseEntity<ProfileDto> {
        val memberId = UUID.fromString(memberIdInHeader)
        val profileDto = profileQueryUseCase.getMyProfile(memberId)
        return ResponseEntity.ok(profileDto)
    }

    @GetMapping("/{memberId}")
    fun getProfile(
        @PathVariable memberId: UUID
    ) : ResponseEntity<ProfileDto> {
        val profileDto = profileQueryUseCase.getMyProfile(memberId)
        return ResponseEntity.ok(profileDto)
    }

    @PutMapping("/nickname")
    fun changeNickname(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeNicknameRequestDto: ChangeNicknameRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val nickname = changeNicknameRequestDto.nickname
        val changeNicknameDto = ChangeNicknameDto(memberId, nickname)
        profileChangeUseCase.changeNickname(changeNicknameDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/birth")
    fun changeBirth(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeBirthRequestDto: ChangeBirthRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val localDate = changeBirthRequestDto.localDate
        val changeBirthDto = ChangeBirthDto(memberId, localDate)
        profileChangeUseCase.changeBirth(changeBirthDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/gender")
    fun changeGender(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeGenderRequestDto: ChangeGenderRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val gender = changeGenderRequestDto.gender
        val changeGenderDto = ChangeGenderDto(memberId, gender)
        profileChangeUseCase.changeGender(changeGenderDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/location")
    fun changeLocation(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeLocationRequestDto: ChangeLocationRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val latitude = changeLocationRequestDto.latitude
        val longitude = changeLocationRequestDto.longitude
        val changeLocationDto = ChangeLocationDto(memberId, latitude, longitude)
        profileChangeUseCase.changeLocation(changeLocationDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/mbti")
    fun changeMbti(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeMbtiRequestDto: ChangeMbtiRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val mbti = changeMbtiRequestDto.mbti
        val changeMbtiDto = ChangeMbtiDto(memberId, mbti)
        profileChangeUseCase.changeMbti(changeMbtiDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/profile-image")
    fun changeProfileImage(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeProfileImageRequestDto: ChangeProfileImageRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val imageUrl = changeProfileImageRequestDto.imageUrl
        val changeProfileImageDto = ChangeProfileImageDto(memberId, imageUrl)
        profileChangeUseCase.changeProfileImage(changeProfileImageDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/categories")
    fun changeCategories(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeCategoriesRequestDto: ChangeCategoriesRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val categories = changeCategoriesRequestDto.categories
        val changeCategoriesDto = ChangeCategoriesDto(memberId, categories)
        profileChangeUseCase.changeCategories(changeCategoriesDto)
        return ResponseEntity.ok().build()
    }
}