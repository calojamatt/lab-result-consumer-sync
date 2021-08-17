/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * EventMessageConsumerManager.java
 */
package com.dyts.lrcs.infrasctructure.events.kafka.consumer.delegate.impl;


import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.ResultLabDto;
import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronizationRedis;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.EventReceiver;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.KafkaConsumer;
import com.dyts.lrcs.infrasctructure.services.redis.api.UserSynchronizationServiceRedis;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * Class to manage the message received from kafka topic
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 20/06/21 10:02 p. m.
 * @since 1.0.0
 */
@Slf4j
@AllArgsConstructor
@Component
public class UserSynchronizationConsumerManagerImpl implements EventReceiver<UserSynchronizationDto> {

    /** the User Dto converter */
    private final Converter<UserSynchronizationRedis, UserSynchronizationDto> dtoUserSynchronizationConverter;

    /** */
    private final UserSynchronizationServiceRedis userSynchronizationServiceRedis;

    /** the kafka consumer */
    private final KafkaConsumer<UserSynchronizationDto> dtoKafkaConsumer;

    /**
     * Post construct to subscribe the class to receive events from kafka
     * */
    @PostConstruct
    void init() {
        this.dtoKafkaConsumer.subscribeEventReceiver(this);
    }

    /**
     * receives the message consumed by kafka consumer
     *
     * @param messages the message received
     * */
    @Async
    @Override
    public void receive(UserSynchronizationDto messages) {

        log.debug("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] processing messages received. Messages to process [{}]",
                messages);
        processMessage(Collections.singletonList(messages));
    }

    /**
     * process the message received and insert user data into database
     * @param messages the message to process
     * */
    private void processMessage(final List<UserSynchronizationDto> messages) {

        try {
            userSynchronizationServiceRedis.saveAll(dtoUserSynchronizationConverter.convert(messages));
            log.info("User Synchronization process, users synchronized.");
        } catch(Exception e) {
            log.warn("[LAB-RESULT-USER-SYNC-PROCESS] Error trying to insert user list. Detail: {}",
                    e.getMessage());
        }
    }
}
