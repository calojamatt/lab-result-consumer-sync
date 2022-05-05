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
import com.dyts.lrcs.infrasctructure.database.postgres.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UsersConverterImpl implements Converter<Users, UserSynchronizationDto> {

    /** the password encoder for the user */
    private final PasswordEncoder passwordEncoder;

    /**
     * Transform Class k to Class t
     *
     * @param userSynchronization the class to convert
     * @return t the new object
     */
    @Override
    public Users convert(UserSynchronizationDto userSynchronization) {

        return Users.builder()
                .withDni(userSynchronization.getDni())
                .withPassword(passwordEncoder.encode(userSynchronization.getDni()))
                .withUsername(userSynchronization.getDni())
                .withDocumentType(userSynchronization.getDniType())
                .withName(userSynchronization.getFirstName())
                .withLastName(userSynchronization.getLastName())
                .withEmail(userSynchronization.getEmail())
                .withState("A")
                .withRol("USUARIO")
                .withSource(userSynchronization.getSource())
                .build();
    }

    /**
     * Transform Class k to Class t
     *
     * @param userSynchronizations a list of k to convert
     * @return List<UserSynchronization> a list of new objects
     */
    @Override
    public List<Users> convert(List<UserSynchronizationDto> userSynchronizations) {

        log.debug("User Synchronization process, converting userSynchronizationList to UserSynchronizationRedisList, " +
                "transforming [{}] records.", userSynchronizations.size());
        return userSynchronizations.stream().map(this::convert).collect(Collectors.toList());
    }
}
