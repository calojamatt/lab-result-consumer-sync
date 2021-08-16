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
package com.dyts.lrcs.infrasctructure.events.kafka.consumer.delegate.api;

import java.util.List;

/**
 * Interface to encapsulate the manager operations and control the kafka consumer operations
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 20/06/21 10:06 p. m.
 * @since 1.0.0
 */
public interface SynchronizationConsumerManager {

    /**
     * receives the message consumed by kafka consumer
     *
     * @param messages the message received
     * */
    void receiveMessage(List<String> messages);
}
