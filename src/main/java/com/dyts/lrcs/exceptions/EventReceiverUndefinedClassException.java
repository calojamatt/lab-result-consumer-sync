/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * EventReceiverUndefinedClassException.java
 */
package com.dyts.lrcs.exceptions;

/**
 * Class type of the message for the event receiver was not found
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 3:02 p. m.
 * @since 1.0.0
 */
public class EventReceiverUndefinedClassException extends RuntimeException {

    /**
     * Default constructor whose receives a {@link Exception}
     *
     * @param e the exception
     */
    public EventReceiverUndefinedClassException(Exception e){
        super(e);
    }
}
