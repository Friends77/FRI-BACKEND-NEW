package com.example.user.domain.exception

class ProfileNotFoundByMemberIdException : BaseException(ErrorCode.PROFILE_NOT_FOUND_BY_MEMBER_ID)

class InvalidProfilePropertyException(message : String) : BaseException(ErrorCode.INVALID_PROFILE_PROPERTY, message)
