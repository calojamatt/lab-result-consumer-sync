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
import com.dyts.lrcs.infrasctructure.database.redis.entity.ResultLab;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.EventReceiver;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.KafkaConsumer;
import com.dyts.lrcs.infrasctructure.services.redis.api.ResultLabService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Component
public class LabExamSynchronizationConsumerManagerImpl implements EventReceiver<ResultLabDto> {

    /** the Result Lab Dto converter */
    private final Converter<ResultLab, ResultLabDto> resultLabConverter;

    /** the result lab service object */
    private final ResultLabService resultLabService;

    /** the kafka consumer */
    private final KafkaConsumer<ResultLabDto> labDtoKafkaConsumer;

    /**
     * Post construct to subscribe the class to receive events from kafka
     * */
    @PostConstruct
    void init() {
        this.labDtoKafkaConsumer.subscribeEventReceiver(this);
    }


    /**
     * Method that should be implemented by event receiver to
     * obtain the message in a custom type format
     * receives the message consumed by kafka consumer
     *
     * @param message the message received in custom type format
     */
    @Async
    @Override
    public void receive(ResultLabDto message) {

        log.debug("[LAB-RESULT-SYNC-PROCESS-CONSUMER] processing messages received. Messages to process [{}]", message);
        processMessage(Collections.singletonList(message));
    }

    /**
     * process the message received and insert user data into database
     * @param messages the message to process
     * */
    private void processMessage(final List<ResultLabDto> messages) {

        try {
            resultLabService.saveAll(resultLabConverter.convert(messages));
        } catch(Exception e) {
            log.warn("[LAB-RESULT-SYNC-PROCESS] Error trying to insert exam result. Detail: {}",
                    e.getMessage());
        }
    }
}
