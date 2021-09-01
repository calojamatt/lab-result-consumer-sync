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


import com.dyts.lrcs.dtos.ResultLabKeyValue;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.EventReceiver;
import com.dyts.lrcs.infrasctructure.events.kafka.consumer.config.KafkaConsumer;
import com.dyts.lrcs.managers.LabExamSynchronizationManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

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
public class LabExamSynchronizationConsumerManagerImpl implements EventReceiver<ResultLabKeyValue> {

    /** the kafka consumer */
    private final KafkaConsumer<ResultLabKeyValue> labDtoKafkaConsumer;

    /** The manager for lab exam synchronization */
    private final LabExamSynchronizationManager labExamSynchronizationManager;

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
    public void receive(ResultLabKeyValue message) {

        log.debug("[LAB-RESULT-SYNC-PROCESS-CONSUMER] processing messages received. Messages to process [{}]", message);
        labExamSynchronizationManager.synchronizeLabExams(Collections.singletonList(message));
    }
}
