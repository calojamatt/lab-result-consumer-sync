/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationRedisConverter.java
 */
package com.dyts.lrcs.converters.impl;

import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronizationRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to implement the transform from UserSynchronization to UserSynchronizationRedis
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 4:03 p. m.
 * @since 1.0.0
 */
@Slf4j
@Component
public class UserSynchronizationRedisConverterImpl implements Converter<UserSynchronizationRedis, UserSynchronizationDto> {

    /**
     * Transform Class k to Class t
     *
     * @param userSynchronization the class to convert
     * @return t the new object
     */
    @Override
    public UserSynchronizationRedis convert(UserSynchronizationDto userSynchronization) {

        return UserSynchronizationRedis.builder()
                .withDniType(userSynchronization.getDniType())
                .withDni(userSynchronization.getDni())
                .withFirstName(userSynchronization.getFirstName())
                .withLastName(userSynchronization.getLastName())
                .withEmail(userSynchronization.getEmail())
                .build();
    }

    /**
     * Transform Class k to Class t
     *
     * @param userSynchronizations a list of k to convert
     * @return List<UserSynchronizationRedis> a list of new objects
     */
    @Override
    public List<UserSynchronizationRedis> convert(List<UserSynchronizationDto> userSynchronizations) {

        log.debug("User Synchronization process, converting userSynchronizationList to UserSynchronizationRedisList, " +
                "transforming [{}] records.", userSynchronizations.size());
        return userSynchronizations.stream().map(this::convert).collect(Collectors.toList());
    }
}
