package com.example.cleanarchitecturepractice.user.domain

import org.junit.jupiter.api.Test

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
class UserTest {

    @Test
    fun `유저 도메인 생성`() {
        val user = User(id = 1, name = "name", email = "email", password = "password", address = "address")
        println(user)
    }

}