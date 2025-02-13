package com.example.user.adapter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["com.example.user.adapter", "com.example.common", "com.example.user.application", "com.example.user.domain"])
@EnableJpaRepositories(basePackages = ["com.example.user.infrastructure"])
@EntityScan(basePackages = ["com.example.user.domain.entity"])
class UserAdapterApplication {
}

fun main(args: Array<String>) {
    runApplication<UserAdapterApplication>(*args)
}