package com.example.friendsmessagecontroller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class FriendsMessageControllerApplication

fun main(args: Array<String>) {
    runApplication<FriendsMessageControllerApplication>(*args)
}
