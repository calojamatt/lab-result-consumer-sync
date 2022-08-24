/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * EventMessageConsumer.java
 */
package com.dyts.lrcs.infrastructure.events.consumer.kafka.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to consume event message in kafka topic
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 20/06/21 6:16 p. m.
 * @since 1.0.0
 */
@Slf4j
@AllArgsConstructor
//@Configuration
//@EnableKafka
public class KafkaMessageConsumer {

    /** the kafka consumer configuration loaded from properties*/
    @Bean
    @ConfigurationProperties(prefix = "spring.kafka.consumer")
    public Map<String, String> kafkaConsumerConfig() {

        return new HashMap<>();
    }

    /** the consumer factory for kafka consumer */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {

        final Map<String, String> loadedConfig = kafkaConsumerConfig();

        final Map<String, Object> consumerConfigs = new HashMap<>();
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, loadedConfig.get("bootstrap-servers"));
        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, loadedConfig.get("group-id"));
        consumerConfigs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, loadedConfig.get("auto-offset-reset"));
        consumerConfigs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, loadedConfig.get("enable-auto-commit"));
        consumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(consumerConfigs);
    }

    /** the Concurrent Kafka Listener Container Factory for kafka consumer */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

}
