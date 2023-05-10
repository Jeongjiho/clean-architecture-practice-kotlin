package com.example.cleanarchitecturepractice.user.adapter.out.persistence.repository

import com.example.cleanarchitecturepractice.user.adapter.out.persistence.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
//@Repository
@Component
interface UserRepository : JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String): Optional<UserEntity>

}