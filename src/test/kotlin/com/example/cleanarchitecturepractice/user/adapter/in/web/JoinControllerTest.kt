package com.example.cleanarchitecturepractice.user.adapter.`in`.web

import com.example.cleanarchitecturepractice.user.adapter.`in`.web.model.UserJoinDto
import com.example.cleanarchitecturepractice.user.application.port.`in`.command.JoinCommand
import com.example.cleanarchitecturepractice.user.application.port.out.LoadEmailPort
import com.example.cleanarchitecturepractice.user.application.port.out.SaveJoinPort
import com.example.cleanarchitecturepractice.user.domain.User
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-22
 */
@SpringBootTest
@AutoConfigureMockMvc
class JoinControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var loadEmailPort: LoadEmailPort

    @MockBean
    lateinit var saveJoinPort: SaveJoinPort

    private val EXIST_EMAIL = "jev@tessa.art"
    private val NOT_EXIST_EMAIL = "jev2@tessa.art"

    @Test
    fun `입력값 유효성 검사`() {

        val userJoinDto = UserJoinDto(id = null, name = "jeongjiho", email = "jev", password = "1234", address = "서울시")
        val requestJson = jacksonObjectMapper().writeValueAsString(userJoinDto)

        val uri = "/user/join"

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Location", """http://127.0.0.1:8080${uri}""")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun `회원 가입 테스트`() {

        val joinCommand = JoinCommand(name = "jeongjiho", email = NOT_EXIST_EMAIL, password = "1234", address = "서울시")
        whenever(loadEmailPort.loadEmail(NOT_EXIST_EMAIL)).thenReturn(null)
        whenever(saveJoinPort.saveJoin(joinCommand.toDomain())).thenReturn(joinCommand.toDomain())

        val userJoinDto = UserJoinDto(id = null, name = "jeongjiho", email = NOT_EXIST_EMAIL, password = "1234", address = "서울시")
        val requestJson = jacksonObjectMapper().writeValueAsString(userJoinDto)

        val uri = "/user/join"

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Location", """http://127.0.0.1:8080${uri}""")
        )
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andDo(MockMvcResultHandlers.print())

//        then(joinUseCase).should()
//                .joinUser(eq(JoinCommand(name = "jev", email = "jev@tessa.art", password = "123", address = "한국")))

    }

    @Test
    fun `회원가입 실패 테스트`() {

        val joinCommand = JoinCommand(name = "jeongjiho", email = EXIST_EMAIL, password = "1234", address = "서울시")
        whenever(loadEmailPort.loadEmail(EXIST_EMAIL)).thenReturn(User(id = 1,name = "jeongjiho", email = EXIST_EMAIL, password = "1234", address = "서울시"))

        val userJoinDto = UserJoinDto(id = null, name = "jev", email = EXIST_EMAIL, password = "123", address = "한국")
        val requestJson = jacksonObjectMapper().writeValueAsString(userJoinDto)

        val uri = "/user/join"

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Location", """http://127.0.0.1:8080${uri}""")
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())



    }

}