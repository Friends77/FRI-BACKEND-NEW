package com.example.message.infrastructure.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory


@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}") private val bootstrapServers: String,
    private val kafkaProducerInterceptor: KafkaProducerInterceptor
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configProps: Map<String, Any> = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        )
        val factory = DefaultKafkaProducerFactory<String, String>(configProps)
        factory.setTransactionIdPrefix("tx-") // 채팅 메세지 저장이 완료되면 카프카 메세지를 커밋하고 컨슈머는 커밋된 메세지만 읽기
        return factory
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        val kafkaTemplate = KafkaTemplate(producerFactory())
        kafkaTemplate.setProducerInterceptor(kafkaProducerInterceptor)
        return kafkaTemplate
    }
}