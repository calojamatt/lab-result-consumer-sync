/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * AbstractKafkaConsumer.java
 */
package com.dyts.lrcs.infrastructure.events.consumer.kafka.config;

import com.dyts.lrcs.converters.impl.JsonConverterImpl;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class that model the behaviour that should have a Kafka consumer concreted class
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 3:04 p. m.
 * @since 1.0.0
 */
public abstract class KafkaConsumerAbstract<T> implements KafkaConsumer<T> {

    /**
     * Set of classes interested in receive a message from consumer
     */
    protected Set<EventReceiver<T>> eventReceivers;

    /**
     * Initialize the receivers list
     */
    @PostConstruct
    public void init() {

        eventReceivers = new HashSet<>();
    }

    /**
     * Method to subscribe objects who needs to receive the kafka message
     * and process it
     *
     * @param eventReceiver the receiver object
     */
    @Override
    public void subscribeEventReceiver(EventReceiver<T> eventReceiver) {

        eventReceivers.add(eventReceiver);
    }

    /**
     * Method to convert the string message to a custom object defined by
     * the receiver in a generic form.
     * {@link JsonConverterImpl} is the class used to convert from String to custom
     * object type.
     *
     * @param message the message in json string format
     */
    protected void convertMessage(final String message) {

        eventReceivers.forEach(e ->
                e.receive(JsonConverterImpl.fromJson(message, e.getBindingClass()))
        );
    }
}
