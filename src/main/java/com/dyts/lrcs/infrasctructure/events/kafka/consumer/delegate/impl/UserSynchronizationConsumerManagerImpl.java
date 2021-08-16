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
import com.dyts.lrcs.converters.impl.JsonConverterImpl;
import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronizationRedis;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.delegate.api.SynchronizationConsumerManager;
import com.dyts.lrcs.infrasctructure.services.redis.api.UserSynchronizationServiceRedis;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
public class UserSynchronizationConsumerManagerImpl implements SynchronizationConsumerManager {

    /** the User Dto converter */
    private final Converter<UserSynchronizationRedis, UserSynchronizationDto> dtoUserSynchronizationConverter;

    /** */
    private final UserSynchronizationServiceRedis userSynchronizationServiceRedis;

    /**
     * receives the message consumed by kafka consumer
     *
     * @param messages the message received
     * */
    @Async
    public void receiveMessage(List<String> messages) {

        log.debug("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] message received");
        processMessage(messages);
    }

    /**
     * process the message received and insert user data into database
     * @param messages the message to process
     * */
    private void processMessage(final List<String> messages) {

        final var userSynchronizationList =
                JsonConverterImpl.fromJson(messages, UserSynchronizationDto.class);
        log.info("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] processing messages received. Messages to process [{}]",
                userSynchronizationList.size());

        try {
            userSynchronizationServiceRedis.saveAll(dtoUserSynchronizationConverter.convert(userSynchronizationList));
            log.info("User Synchronization process, users synchronized.");
        } catch(Exception e) {
            log.warn("[LAB-RESULT-USER-SYNC-PROCESS] Error trying to insert user list. Detail: {}",
                    e.getMessage());
        }
    }
}
