package com.example.cleanarchitecturepractice.user.application.port.out

import com.example.cleanarchitecturepractice.user.domain.User

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
interface LoadEmailPort {

    fun loadEmail(email: String) : User?

}