package com.example.user.application.exception

import com.example.common.exception.BaseException
import com.example.common.exception.ErrorCode


abstract class MemberException(errorCode: ErrorCode) : BaseException(errorCode) {
}

class MemberNotFoundByEmailException : MemberException(ErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)