package com.example.friendservernew.auth.service


import com.example.friendservernew.auth.controller.LoginRequestDto
import com.example.friendservernew.auth.controller.LoginResponseDto
import com.example.friendservernew.auth.controller.RefreshRequestDto
import com.example.friendservernew.auth.controller.RefreshResponseDto
import com.example.friendservernew.auth.controller.RegisterRequestDto
import com.example.friendservernew.auth.exception.InvalidJwtTokenException
import com.example.friendservernew.auth.service.atrt.AtRtService
import com.example.friendservernew.auth.service.authmail.AuthMailJwtValidator
import com.example.friendservernew.member.service.MemberCommandService
import com.example.friendservernew.security.userdetails.CustomUserDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val memberCommandService: MemberCommandService,
    private val authValidator: AuthValidator,
    private val authMailJwtValidator: AuthMailJwtValidator,
    private val authenticationManager: AuthenticationManager,
    private val atRtService: AtRtService,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun register(registerRequestDto: RegisterRequestDto) {
        // 이메일 인증 토큰 검사
        val validateEmailAuthTokenDto = AuthMapper.registerRequestDtoToValidateEmailAuthTokenDto(registerRequestDto)
        authMailJwtValidator.validateEmailAuthToken(validateEmailAuthTokenDto)

        // 회원가입 정보 검사 (이메일 패턴, 닉네임 패턴, 비밀번호 패턴)
        val userDto = AuthMapper.registerRequestDtoToUserDto(registerRequestDto)
        authValidator.registerValidator(userDto)

        // 비밀번호 암호화
        val encodedPassword = passwordEncoder.encode(userDto.password)
        val createUserDto = AuthMapper.userDtoToCreateUserDto(userDto, encodedPassword)

        memberCommandService.createUser(createUserDto)
    }

    @Transactional(readOnly = true)
    fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        val email = loginRequestDto.email
        val password = loginRequestDto.password
        val authentication =
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(email, password),
            )

        val userDetails = authentication.principal as CustomUserDetails
        val memberId = userDetails.memberId
        val authorities = userDetails.authorities

        val userTokenDto = UserTokenDto(memberId = memberId, authorities = authorities)
        val atRtDto = atRtService.createAtRt(userTokenDto)

        val loginResponseDto = AuthMapper.atRtDtoToLoginResponseDto(atRtDto)
        return loginResponseDto
    }

    @Transactional(readOnly = true)
    fun refresh(refreshRequestDto: RefreshRequestDto): RefreshResponseDto {
        val refreshToken = refreshRequestDto.refreshToken
        if (!atRtService.isValidRefreshToken(refreshToken)) {
            throw InvalidJwtTokenException()
        }

        val memberId = atRtService.getMemberId(refreshToken)
        val authorities = atRtService.getAuthorities(refreshToken)

        val userTokenDto = UserTokenDto(memberId = memberId, authorities = authorities)
        val atRtDto = atRtService.createAtRt(userTokenDto)

        val refreshResponseDto = AuthMapper.atRtDtoToRefreshResponseDto(atRtDto)
        return refreshResponseDto
    }
}
