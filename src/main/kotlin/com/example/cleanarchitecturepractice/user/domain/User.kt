package com.example.cleanarchitecturepractice.user.domain

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-21
 */
data class User(
        val id: Long?,
        val name: String,
        val email: String,
        val password: String,
        val address: String
) {

    // TODO(Domain의 활용 메서드..)


}