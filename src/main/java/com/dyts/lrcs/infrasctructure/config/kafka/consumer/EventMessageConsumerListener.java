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
package com.dyts.lrcs.infrasctructure.config.kafka.consumer;

import com.dyts.lrcs.infrasctructure.events.kafka.api.EventMessageConsumerManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

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
@EnableKafka
public class EventMessageConsumerListener {

    /** the user synchronization topic name */
    @Value(value = "${lab-result.user-sync.topic.name}")
    private String labResultUserSyncTopicName;

    /** the event message consumer manager*/
    private final EventMessageConsumerManager consumerManager;

    /**
     * the kafka consumer message listener
     * @param messages a list of messages consumed
     * */
    @KafkaListener(topics = "${lab-result.user-sync.topic.name}",
            groupId = "${kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(List<String> messages) {

        log.info("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] consuming message from TOPIC [{}], messages [{}]",
                labResultUserSyncTopicName,
                messages);
        consumerManager.receiveMessage(messages);
    }
}
