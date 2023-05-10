package com.example.cleanarchitecturepractice.user.adapter.out.persistence.model

import com.example.cleanarchitecturepractice.common.annotation.NoArg
import com.example.cleanarchitecturepractice.user.domain.User
import jakarta.persistence.*
import lombok.Data

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-30
 */
@Entity
@Table(name = "User")
@NoArg
class UserEntity(
        id: Long?,
        name: String,
        email: String,
        password: String,
        address: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = id

    @Column(name = "name", nullable = false)
    var name: String = name

    @Column(name = "email", nullable = false, unique = true)
    var email: String = email

    @Column(name = "password", nullable = false)
    var password: String = password

    @Column(name = "address", nullable = false)
    var address: String = address

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(id = user.id, name = user.name, email = user.email, password = user.password, address = user.address)
        }
    }

    fun toDomain(): User {
        return User(id = this.id, name = this.name, email = this.email, password = this.password, address = this.address)
    }

}