package com.example.cleanarchitecturepractice.user.application.port.`in`.command

import com.example.cleanarchitecturepractice.common.validation.SelfValidation
import com.example.cleanarchitecturepractice.user.domain.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
data class JoinCommand(
        @field:NotBlank(message = "이름은 필수 입력 값 입니다.")
        val name: String,

        @field:NotBlank(message = "이메일은 필수 입력 값 입니다.")
        @field:Email(message = "이메일을 형식을 확인해주세요.")
        val email: String,

        @field:NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
        val password: String,

        @field:NotBlank(message = "주소는 필수 입력 값 입니다.")
        val address: String
) : SelfValidation() {

    init {
        this.validationSelf()
    }

    fun toDomain(): User {
        return User(id = null, name = this.name, email = this.email, password = this.password, address = this.address)
    }

}