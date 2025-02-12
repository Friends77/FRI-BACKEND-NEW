package com.example.friendsdiscovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class FriendsDiscoveryApplication

fun main(args: Array<String>) {
    runApplication<FriendsDiscoveryApplication>(*args)
}
