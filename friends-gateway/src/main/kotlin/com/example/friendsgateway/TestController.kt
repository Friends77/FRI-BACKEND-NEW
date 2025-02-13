package com.example.friendsgateway

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    @Value("\${jwt.secret-key}") private val key: String,
) {
    @GetMapping("/health")
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok(key)
    }
}