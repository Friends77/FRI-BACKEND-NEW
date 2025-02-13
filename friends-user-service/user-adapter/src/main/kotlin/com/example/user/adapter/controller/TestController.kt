package com.example.user.adapter.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/health")
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("ok")
    }
}