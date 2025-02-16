package com.example.friendsimageservice.controller

import com.example.friendsimageservice.service.ImageUploader
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/user/image")
class ImageController(
    private val imageUploader: ImageUploader
) {
    @PostMapping("/upload")
    fun uploadImage(
        @RequestPart image: MultipartFile
    ) : ResponseEntity<String> {
        try {
            val imageUrl = imageUploader.uploadImage(image)
            return ResponseEntity.ok(imageUrl)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }
}