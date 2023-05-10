package com.example.cleanarchitecturepractice.user.adapter.out.persistence.adapter

import com.example.cleanarchitecturepractice.user.adapter.out.persistence.repository.UserRepository
import com.example.cleanarchitecturepractice.user.adapter.out.persistence.model.UserEntity
import com.example.cleanarchitecturepractice.user.application.port.out.LoadEmailPort
import com.example.cleanarchitecturepractice.user.application.port.out.SaveJoinPort
import com.example.cleanarchitecturepractice.user.domain.User
import org.springframework.stereotype.Component

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
@Component
class UserPersistenceAdapter(
        private val userRepository: UserRepository
) : LoadEmailPort, SaveJoinPort {

    override fun loadEmail(email: String): User? {
        return userRepository.findByEmail(email).orElse(null)?.toDomain()
    }

    override fun saveJoin(user: User): User {
        val userEntity = UserEntity.fromDomain(user)
        userRepository.save(userEntity)
        return userEntity.toDomain()
    }

}