package com.example.user.domain.exception

class ProfileNotFoundByMemberIdException : BaseException(ErrorCode.PROFILE_NOT_FOUND_BY_MEMBER_ID)

class IllegalProfileArgumentException(message : String) : BaseException(ErrorCode.ILLEGAL_PROFILE_ARGUMENT, message)
