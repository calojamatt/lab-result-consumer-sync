/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * ResultLabConverter.java
 */
package com.dyts.lrcs.converters.impl;

import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.ResultLabDto;
import com.dyts.lrcs.infrasctructure.database.redis.entity.ResultLab;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to transform a ResultLabDto into a ResultLab
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:36 p. m.
 * @since 1.0.0
 */
@Slf4j
@Component
public class ResultLabConverter implements Converter<ResultLab, ResultLabDto> {

    /**
     * Transform Class k to Class t
     *
     * @param resultLabDto the class to convert
     * @return t the new object
     */
    @Override
    public ResultLab convert(ResultLabDto resultLabDto) {

        return ResultLab.builder()
                .withPatientCode(resultLabDto.getPatientCode())
                .withFirstName(resultLabDto.getFirstName())
                .withLastName(resultLabDto.getLastName())
                .withFullName(resultLabDto.getFullName())
                .withCreationDate(resultLabDto.getCreationDate())
                .withCreationHour(resultLabDto.getCreationHour())
                .withDocumentType(resultLabDto.getDocumentType())
                .withDni(resultLabDto.getDni())
                .withPatientClteCode(resultLabDto.getPatientClteCode())
                .withExamCode(resultLabDto.getExamCode())
                .withExamName(resultLabDto.getExamName())
                .withBalance(resultLabDto.getBalance())
                .withCompanyDni(resultLabDto.getCompanyDni())
                .withSource(resultLabDto.getSource())
                .build();
    }

    /**
     * Transform Class k to Class t
     *
     * @param k a list of k to convert
     * @return t a list of new objects
     */
    @Override
    public List<ResultLab> convert(List<ResultLabDto> k) {

        log.debug("Result Lab synchronization process, converting ResultLabDtoList to ResultLabList, " +
                "transforming [{}] records.", k.size());
        return k.stream().map(this::convert).collect(Collectors.toList());
    }
}
