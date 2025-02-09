package com.example.friendservernew.member

import com.atelier.server.common.exception.CustomException
import com.atelier.server.common.exception.ErrorCode

abstract class MemberException(errorCode: ErrorCode) : CustomException(errorCode) {
}

class MemberNotFoundByEmailException : MemberException(ErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)