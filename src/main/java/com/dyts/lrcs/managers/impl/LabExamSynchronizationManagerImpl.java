/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * LabExamSynchronizationManagerImpl.java
 */
package com.dyts.lrcs.managers.impl;

import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.ResultLabDto;
import com.dyts.lrcs.dtos.ResultLabKeyValue;
import com.dyts.lrcs.infrasctructure.database.postgres.entity.ResultLab;
import com.dyts.lrcs.infrasctructure.services.postgres.api.ResultLabService;
import com.dyts.lrcs.managers.LabExamSynchronizationManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to implement the LabExamSynchronizationManager operations to process the Lab Exams
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 17/08/21 9:03 p. m.
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class LabExamSynchronizationManagerImpl implements LabExamSynchronizationManager {

    /** the result lab service object */
    private final ResultLabService resultLabService;

    /** the Result Lab Dto converter */
    private final Converter<ResultLab, ResultLabDto> resultLabConverter;

    /**
     * Process the messages received and synchronize the Lab Exams
     *
     * @param resultLabDtoList the List of {@link ResultLabKeyValue} to process
     */
    @Override
    public void synchronizeLabExams(List<ResultLabKeyValue> resultLabDtoList) {

        var labExamToSave = resultLabDtoList.stream()
                .map(resultLabDto -> {
                    var resultLabCount = resultLabService.countByPatientCode(resultLabDto.getPatientCode());
                    return resultLabCount == 0 ? resultLabDto : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (!labExamToSave.isEmpty()) {
            try {
                labExamToSave.forEach(resultLabKeyValue ->
                        resultLabService.saveAll(resultLabConverter.convert(resultLabKeyValue.getExamList())));
            } catch(Exception e) {
                log.warn("[LAB-RESULT-SYNC-PROCESS] Error trying to insert exam result. Detail: {}",
                        e.getMessage());
            }
        }
    }
}
