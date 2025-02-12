package com.example.user.adapter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.example.user.adapter", "com.example.common"])
class UserAdapterApplication {
}

fun main(args: Array<String>) {
    runApplication<UserAdapterApplication>(*args)
}