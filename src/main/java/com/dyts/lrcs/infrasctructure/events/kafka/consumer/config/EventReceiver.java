/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * EventReceiver.java
 */
package com.dyts.lrcs.infrasctructure.events.kafka.consumer.config;

import com.dyts.lrcs.exceptions.EventReceiverUndefinedClassException;

import java.lang.reflect.ParameterizedType;

/**
 * Interface that define the methods that should have a event receiver concreted class
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 3:01 p. m.
 * @since 1.0.0
 */
public interface EventReceiver<T> {

    /**
     * Method that should be implemented by event receiver to
     * obtain the message in a custom type format
     *
     * @param message the message in custom type format
     */
    void receive(T message);

    /**
     * Method to determine the custom type of the object needed
     * by the event receiver object, defined in the T generic
     *
     * @return the type of the T generic
     */
    default Class<T> getBindingClass() {

        try {
            return (Class<T>) Class.forName(((Class<T>) ((ParameterizedType) getClass()
                    .getGenericInterfaces()[0]).getActualTypeArguments()[0]).getTypeName());
        } catch (Exception e) {
            throw new EventReceiverUndefinedClassException(e);
        }
    }
}
