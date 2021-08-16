/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * KafkaConsumer.java
 */
package com.dyts.lrcs.infrasctructure.events.kafka.consumer.config;

/**
 *  Interface that define the methods that should have a Kafka consumer concreted class
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 2:59 p. m.
 * @since 1.0.0
 */
public interface KafkaConsumer<T> {

    /**
     * Method to receive and process a message from Kafka topic
     *
     * @param message the message in String format
     */
    void receive(String message);

    /**
     * Method to subscribe objects who needs to receive the kafka message
     * and process it
     *
     * @param eventReceiver the receiver object
     */
    void subscribeEventReceiver(EventReceiver<T> eventReceiver);
}
