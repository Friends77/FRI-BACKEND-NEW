package com.example.user.domain.exception


class MemberNotFoundException(message : String?)
    : BaseException(ErrorCode.NOT_FOUND_MEMBER, message ?: )