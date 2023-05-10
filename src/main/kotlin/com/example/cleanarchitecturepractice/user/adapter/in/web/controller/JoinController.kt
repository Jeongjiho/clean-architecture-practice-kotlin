package com.example.cleanarchitecturepractice.user.adapter.`in`.web.controller

import com.example.cleanarchitecturepractice.user.adapter.`in`.web.model.UserJoinDto
import com.example.cleanarchitecturepractice.user.application.port.`in`.JoinUseCase
import com.example.cleanarchitecturepractice.user.common.annotation.UserController
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import java.net.URI
import javax.xml.stream.Location

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-21
 */
@UserController
class JoinController(
        private val joinUseCase: JoinUseCase
) {

    @PostMapping(value = ["/join"])
    @ResponseStatus(HttpStatus.CREATED)
    fun join(@RequestHeader("Location") location: String, @RequestBody @Valid userJoinDto: UserJoinDto): ResponseEntity<UserJoinDto> {
        val user = joinUseCase.joinUser(userJoinDto.toCommand())
        return ResponseEntity.created(URI.create(location)).body(UserJoinDto.fromDomain(user))
    }

}