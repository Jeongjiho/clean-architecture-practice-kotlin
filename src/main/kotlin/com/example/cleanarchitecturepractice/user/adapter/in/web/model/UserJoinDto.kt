package com.example.cleanarchitecturepractice.user.adapter.`in`.web.model

import com.example.cleanarchitecturepractice.common.annotation.NoArg
import com.example.cleanarchitecturepractice.user.application.port.`in`.command.JoinCommand
import com.example.cleanarchitecturepractice.user.domain.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-30
 */
@NoArg
data class UserJoinDto(

        val id: Long?,

        @field:NotBlank(message = "이름은 필수 입력 값 입니다.")
        val name: String,

        @field:NotBlank(message = "이메일은 필수 입력 값 입니다.")
        @field:Email(message = "이메일을 형식을 확인해주세요.")
        val email: String,

        @field:NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
        val password: String,

        @field:NotBlank(message = "주소는 필수 입력 값 입니다.")
        val address: String
) {

        companion object {
                fun fromDomain(user: User): UserJoinDto {
                        return UserJoinDto(id = user.id, name = user.name, email = user.email, password = user.password, address = user.address)
                }
        }

        fun toDomain(): User {
                return User(id = null, name = this.name, email = this.email, password = this.password, address = this.address)
        }

        fun toCommand(): JoinCommand {
                return JoinCommand(name = this.name, email = this.email, password = this.password, address = this.address)
        }
}