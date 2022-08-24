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
import com.dyts.lrcs.infrastructure.database.postgres.entity.Users;
import com.dyts.lrcs.infrastructure.database.postgres.entity.UsersSynchronization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to implement the transform from UserSynchronization to UserSynchronization
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 4:03 p. m.
 * @since 1.0.0
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class UsersSynchronizationConverterImpl implements Converter<UsersSynchronization, Users> {

    /**
     * Transform Class k to Class t
     *
     * @param userSynchronization the class to convert
     * @return t the new object
     */
    @Override
    public UsersSynchronization convert(Users userSynchronization) {

        return UsersSynchronization.builder()
                .withDni(userSynchronization.getDni())
                .withDocumentType(userSynchronization.getDocumentType())
                .withFirstName(userSynchronization.getName())
                .withLastName(userSynchronization.getLastName())
                .withEmail(userSynchronization.getEmail())
                .build();
    }

    /**
     * Transform Class k to Class t
     *
     * @param userSynchronizations a list of k to convert
     * @return List<UserSynchronization> a list of new objects
     */
    @Override
    public List<UsersSynchronization> convert(List<Users> userSynchronizations) {

        log.debug("User Synchronization process, converting userSynchronizationList to UserSynchronizationRedisList, " +
                "transforming [{}] records.", userSynchronizations.size());
        return userSynchronizations.stream().map(this::convert).collect(Collectors.toList());
    }
}
