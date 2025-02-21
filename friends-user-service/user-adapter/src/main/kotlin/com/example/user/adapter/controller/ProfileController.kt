package com.example.user.adapter.controller

import com.example.auth.application.dto.ChangeBirthDto
import com.example.auth.application.dto.ChangeCategoriesDto
import com.example.auth.application.dto.ChangeGenderDto
import com.example.auth.application.dto.ChangeLocationDto
import com.example.auth.application.dto.ChangeMbtiDto
import com.example.auth.application.dto.ChangeNicknameDto
import com.example.auth.application.dto.ChangeProfileImageDto
import com.example.auth.application.dto.ChangeSelfDescriptionDto
import com.example.auth.application.dto.ProfileDto
import com.example.auth.application.service.ProfileChangeService
import com.example.auth.application.service.ProfileQueryService
import com.example.user.adapter.ChangeBirthRequestDto
import com.example.user.adapter.ChangeCategoriesRequestDto
import com.example.user.adapter.ChangeGenderRequestDto
import com.example.user.adapter.ChangeLocationRequestDto
import com.example.user.adapter.ChangeMbtiRequestDto
import com.example.user.adapter.ChangeNicknameRequestDto
import com.example.user.adapter.ChangeProfileImageRequestDto
import com.example.user.adapter.ChangeSelfDescriptionRequestDto
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
    private val profileChangeService: ProfileChangeService,
    private val profileQueryService: ProfileQueryService
){
    @GetMapping
    fun getMyProfile(
        @RequestHeader("X-Member-Id") memberIdInHeader : String
    ) : ResponseEntity<ProfileDto> {
        val memberId = UUID.fromString(memberIdInHeader)
        val profileDto = profileQueryService.getProfile(memberId)
        return ResponseEntity.ok(profileDto)
    }

    @GetMapping("/{memberId}")
    fun getProfile(
        @PathVariable memberId: UUID
    ) : ResponseEntity<ProfileDto> {
        val profileDto = profileQueryService.getProfile(memberId)
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
        profileChangeService.changeNickname(changeNicknameDto)
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
        profileChangeService.changeBirth(changeBirthDto)
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
        profileChangeService.changeGender(changeGenderDto)
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
        profileChangeService.changeLocation(changeLocationDto)
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
        profileChangeService.changeMbti(changeMbtiDto)
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
        profileChangeService.changeProfileImage(changeProfileImageDto)
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
        profileChangeService.changeCategories(changeCategoriesDto)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/self-description")
    fun changeSelfDescription(
        @RequestHeader("X-Member-Id") memberIdInHeader : String,
        @RequestBody changeSelfDescriptionRequestDto: ChangeSelfDescriptionRequestDto
    ) : ResponseEntity<String> {
        val memberId = UUID.fromString(memberIdInHeader)
        val selfDescription = changeSelfDescriptionRequestDto.selfDescription
        val changeSelfDescriptionDto = ChangeSelfDescriptionDto(memberId, selfDescription)
        profileChangeService.changeSelfDescription(changeSelfDescriptionDto)
        return ResponseEntity.ok().build()
    }
}