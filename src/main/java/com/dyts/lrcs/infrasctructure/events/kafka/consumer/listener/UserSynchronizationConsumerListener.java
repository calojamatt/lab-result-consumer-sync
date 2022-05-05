/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * EventMessageConsumerListener.java
 */
package com.dyts.lrcs.infrasctructure.events.kafka.consumer.listener;

import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.KafkaConsumerAbstract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Class to implement the kafka consumer listener
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 9:56 p. m.
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserSynchronizationConsumerListener extends KafkaConsumerAbstract<UserSynchronizationDto> {

    /** the user synchronization topic name */
    @Value(value = "${lab-result.user-sync.topic.name}")
    private String labResultUserSyncTopicName;

    /**
     * the kafka consumer message listener
     * Method to receive and process a message from Kafka topic
     *
     * @param message the message in String format
     */
    @KafkaListener(topics = "${lab-result.user-sync.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void receive(String message) {

        log.info("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] consuming message from TOPIC [{}], messages [{}]",
                labResultUserSyncTopicName,
                message);
        convertMessage(message);
    }
}
