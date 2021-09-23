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
import com.dyts.lrcs.infrasctructure.database.postgres.entity.ResultLab;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * Class to transform a ResultLabDto into a ResultLab
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:36 p. m.
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ResultLabConverter implements Converter<ResultLab, ResultLabDto> {

    /** the generic mapper */
    private final ModelMapper modelMapper;

    /**
     * Transform Class k to Class t
     *
     * @param resultLabDto the class to convert
     * @return t the new object
     */
    @Override
    public ResultLab convert(ResultLabDto resultLabDto) {

        final ResultLab resultLab = modelMapper.map(resultLabDto, ResultLab.class);
        resultLab.setId(UUID.randomUUID().toString());
        resultLab.setValidSign(Objects.nonNull(resultLabDto.getValidSign()) ? resultLabDto.getValidSign() : null);
        resultLab.setResultImage(Objects.nonNull(resultLabDto.getResultImage()) ? resultLabDto.getResultImage() : null);
        resultLab.setSecondSign(Objects.nonNull(resultLabDto.getSecondSign()) ? resultLabDto.getSecondSign() : null);

        return resultLab;
    }
}
