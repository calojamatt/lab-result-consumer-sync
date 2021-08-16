/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationManager.java
 */
package com.dyts.lrcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *LabResultUserSyncApplication main class
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 26/08/21 01:49 p. m.
 * @since 1.0.0
 */
@SpringBootApplication
public class LabResultConsumerSync {

    public static void main(String[] args) {
        SpringApplication.run(LabResultConsumerSync.class, args);
    }

}
