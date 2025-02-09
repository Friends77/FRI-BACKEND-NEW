package com.example.friendservernew.member

import com.example.friendservernew.common.exception.CustomException
import com.example.friendservernew.common.exception.ErrorCode


abstract class MemberException(errorCode: ErrorCode) : CustomException(errorCode) {
}

class MemberNotFoundByEmailException : MemberException(ErrorCode.NOT_FOUND_MEMBER_BY_EMAIL)