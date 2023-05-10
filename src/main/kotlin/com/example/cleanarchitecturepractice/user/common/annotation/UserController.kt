package com.example.cleanarchitecturepractice.user.common.annotation

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author jev
 * @version 1.0
 * @since 2023-03-30
 */
@RestController
@RequestMapping(value = ["/user"])
annotation class UserController {
}