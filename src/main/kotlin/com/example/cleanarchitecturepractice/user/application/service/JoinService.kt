 package com.example.cleanarchitecturepractice.user.application.service

import com.example.cleanarchitecturepractice.common.exception.UserExistsException
import com.example.cleanarchitecturepractice.user.application.port.`in`.JoinUseCase
import com.example.cleanarchitecturepractice.user.application.port.`in`.command.JoinCommand
import com.example.cleanarchitecturepractice.user.application.port.out.LoadEmailPort
import com.example.cleanarchitecturepractice.user.application.port.out.SaveJoinPort
import com.example.cleanarchitecturepractice.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

 /**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-21
 */
@Service
open class JoinService(
        private val loadEmailPort: LoadEmailPort,
        private val saveJoinPort: SaveJoinPort
) : JoinUseCase {

    @Transactional
    override fun joinUser(joinCommand: JoinCommand): User {
        //TODO 비즈니스 유효성 검사

        val user = joinCommand.toDomain()
        loadEmailPort.loadEmail(user.email)?.let { throw UserExistsException("해당 이메일은 이미 사용 중 입니다.") }
        return saveJoinPort.saveJoin(user)
    }


}