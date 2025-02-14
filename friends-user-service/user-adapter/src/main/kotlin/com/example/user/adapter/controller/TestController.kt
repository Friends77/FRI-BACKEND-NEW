package com.example.user.adapter.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/api/global/health")
    fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/api/user/health")
    fun userHealth(): ResponseEntity<String> {
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/api/admin/health")
    fun adminHealth(): ResponseEntity<String> {
        return ResponseEntity.ok("ok")
    }
}