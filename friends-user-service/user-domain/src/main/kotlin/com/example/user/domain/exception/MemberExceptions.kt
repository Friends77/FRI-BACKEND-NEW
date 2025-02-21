package com.example.user.domain.exception


class MemberNotFoundException(message : String? = null) : BaseException(ErrorCode.NOT_FOUND_MEMBER, message)