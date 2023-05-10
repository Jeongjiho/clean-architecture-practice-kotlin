package com.example.cleanarchitecturepractice.user.application.`in`

import com.example.cleanarchitecturepractice.user.application.service.JoinService
import com.example.cleanarchitecturepractice.user.application.port.`in`.JoinUseCase
import com.example.cleanarchitecturepractice.user.application.port.`in`.command.JoinCommand
import com.example.cleanarchitecturepractice.user.application.port.out.LoadEmailPort
import com.example.cleanarchitecturepractice.user.application.port.out.SaveJoinPort
import com.example.cleanarchitecturepractice.user.domain.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
@ExtendWith(MockitoExtension::class)
class JoinUseCaseTest {

    @Mock
    lateinit var loadEmailPort: LoadEmailPort

    @Mock
    lateinit var saveJoinPort: SaveJoinPort

    lateinit var joinUseCase: JoinUseCase

    private val EXIST_EMAIL = "jev@tessa.art"
    private val NOT_EXIST_EMAIL = "jev2@tessa.art"

    @BeforeEach
    fun before() {
        MockitoAnnotations.openMocks(this)
        joinUseCase = JoinService(loadEmailPort, saveJoinPort)
    }

    @Test
    fun `입력값 유효성 검사`() {
        //when
        val joinCommand = JoinCommand(name = "jeongjiho", email = "jev", password = "1234", address = "서울시")
    }

    @Test
    fun `이메일 중복 테스트`() {

        //given
        whenever(loadEmailPort.loadEmail(EXIST_EMAIL)).thenReturn(User(id = 1,name = "jeongjiho", email = EXIST_EMAIL, password = "1234", address = "서울시"))

        //when
        val joinCommand = JoinCommand(name = "jeongjiho", email = EXIST_EMAIL, password = "1234", address = "서울시")
        joinUseCase.joinUser(joinCommand)

    }

    @Test
    fun `가입 성공 테스트`() {

        val joinCommand = JoinCommand(name = "jeongjiho", email = NOT_EXIST_EMAIL, password = "1234", address = "서울시")

        //given
        whenever(loadEmailPort.loadEmail(NOT_EXIST_EMAIL)).thenReturn(null)
        whenever(saveJoinPort.saveJoin(joinCommand.toDomain())).thenReturn(joinCommand.toDomain())

        //when
        val joinResult = joinUseCase.joinUser(joinCommand)

        //then
        Assertions.assertEquals(joinResult, joinCommand.toDomain())

    }

}