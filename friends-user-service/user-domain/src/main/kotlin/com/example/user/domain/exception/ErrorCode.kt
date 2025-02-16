package com.example.user.domain.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    private val errorMessageTemplate: String,
) {
    // Auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    MISSING_JWT_PAYLOAD(HttpStatus.UNAUTHORIZED, "JWT 토큰에 %s가 존재하지 않습니다."),
    NOT_FOUND_MEMBER_BY_EMAIL(HttpStatus.NOT_FOUND, "해당 이메일의 유저가 존재하지 않습니다."),
    EMAIL_AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "이메일 인증에 실패하였습니다."),
    INVALID_EMAIL_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 이메일 JWT입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    INVALID_EMAIL_PATTERN(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일 형식입니다."),
    INVALID_NICKNAME_PATTERN(HttpStatus.BAD_REQUEST, "유효하지 않은 닉네임 형식입니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, "유효하지 않은 비밀번호 형식입니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "로그인에 실패하였습니다."),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이메일 전송에 실패하였습니다."),
    INVALID_EMAIL_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일 인증 코드입니다."),
    OAUTH2_FETCH_FAILED(HttpStatus.UNAUTHORIZED, "[%s] OAuth2 정보를 가져오는데 실패하였습니다."),
    ALREADY_REGISTERED_ANOTHER_METHOD(HttpStatus.BAD_REQUEST, "이미 다른 방식으로 가입된 이메일입니다."),
    INVALID_OAUTH2_PROVIDER(HttpStatus.BAD_REQUEST, "유효하지 않은 OAuth2 서비스입니다."),
    OAUTH2_DATA_EXTRACT_FAILED(HttpStatus.UNAUTHORIZED, "OAuth2 정보 추출에 실패하였습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 Refresh Token입니다."),
    OAUTH2_USER_PASSWORD_CHANGE(HttpStatus.BAD_REQUEST, "OAuth2 유저의 비밀번호 변경은 불가능합니다."),
    PASSWORD_EQUAL_LAST_PASSWORD(HttpStatus.BAD_REQUEST, "이전 비밀번호와 동일한 비밀번호는 사용할 수 없습니다."),

    // Member
    NOT_FOUND_MEMBER_BY_ID(HttpStatus.NOT_FOUND,  "해당 ID의 유저가 존재하지 않습니다."),

    // Profile
    PROFILE_NOT_FOUND_BY_MEMBER_ID(HttpStatus.NOT_FOUND, "해당 ID의 프로필이 존재하지 않습니다."),
    INVALID_PROFILE_PROPERTY(HttpStatus.BAD_REQUEST, "%s"),

    ;
    fun formatErrorMessage(vararg args: Any): String =
        if (args.isNotEmpty()) {
            String.format(errorMessageTemplate, *args)
        } else {
            errorMessageTemplate
        }
}