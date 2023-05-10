package com.example.cleanarchitecturepractice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
open class CleanArchitecturePracticeApplication

fun main(args: Array<String>) {
    runApplication<CleanArchitecturePracticeApplication>(*args)
}