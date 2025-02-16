package com.example.friendsimageservice.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Component
class ImageUploaderImpl(
    private val amazonS3: AmazonS3,
    @Value("\${cloud.aws.s3.bucket}") private val bucketName: String,
    @Value("\${cloud.aws.s3.region.static}") private var region: String
) : ImageUploader {
    override fun uploadImage(image: MultipartFile): String {
        val filename = UUID.randomUUID().toString()

        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = image.contentType
        objectMetadata.contentLength = image.size

        amazonS3.putObject(bucketName, filename, image.inputStream, objectMetadata)

        return "https://$bucketName.s3.$region.amazonaws.com/$filename"
    }
}