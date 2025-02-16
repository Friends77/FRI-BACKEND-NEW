package com.example.user.domain.exception

class MemberNotFoundByEmailException : BaseException(ErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)

class MemberNotFoundByIdException : BaseException(ErrorCode.NOT_FOUND_MEMBER_BY_ID)