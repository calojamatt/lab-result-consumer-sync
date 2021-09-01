/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * LabExamSynchronizationManager.java
 */
package com.dyts.lrcs.managers;

import com.dyts.lrcs.dtos.ResultLabKeyValue;

import java.util.List;

/**
 * Interface to define operations to manage Redis Lab Exam Synchronization operations
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 17/08/21 8:59 p. m.
 * @since 1.0.0
 */
public interface LabExamSynchronizationManager {

    /**
     * Process the messages received and synchronize the Lab Exams
     *
     * @param resultLabDtoList the List of {@link ResultLabKeyValue} to process
     * */
    void synchronizeLabExams(List<ResultLabKeyValue> resultLabDtoList);
}
