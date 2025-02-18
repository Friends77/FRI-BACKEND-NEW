package com.example.friendsmessagecontroller.config

import org.apache.kafka.clients.producer.ProducerInterceptor
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class KafkaProducerInterceptor : ProducerInterceptor<String, String> {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun onSend(record: ProducerRecord<String, String>): ProducerRecord<String, String> {
        logger.info("message body -> ${record.value()}")
        logger.info("message header -> ${record.headers()}")
        return record
    }

    override fun onAcknowledgement(metadata: RecordMetadata?, exception : Exception?) {
        if (exception != null) {
            logger.error("error -> {}", exception.message)
        }
        logger.info("topic -> ${metadata?.topic()}")
        logger.info("partition -> ${metadata?.partition()}")
    }

    override fun configure(p0: MutableMap<String, *>?) {

    }

    override fun close() {
        TODO("Not yet implemented")
    }
}