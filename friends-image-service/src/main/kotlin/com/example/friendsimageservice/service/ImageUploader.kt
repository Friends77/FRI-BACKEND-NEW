package com.example.friendsimageservice.service

import org.springframework.web.multipart.MultipartFile

interface ImageUploader {
    fun uploadImage(image: MultipartFile) : String
}