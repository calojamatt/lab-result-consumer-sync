/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserDTOConverter.java
 */
package com.dyts.lrcs.converters.impl;

import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.UserDTO;
import com.dyts.lrcs.dtos.UserSynchronizationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to transform a User Synchronization into a UserDTO
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 22/06/21 10:28 p. m.
 * @since 1.0.0
 */
@Slf4j
@Component
public class UserDTOConverterImpl implements Converter<UserDTO, UserSynchronizationDto> {

    /**
     * Transform Class UserSynchronization to Class UserDTO
     *
     * @param userSynchronization the class to convert
     * @return UserDTO the new object
     */
    @Override
    public UserDTO convert(UserSynchronizationDto userSynchronization) {

        return UserDTO.builder()
                .withDocumentType(userSynchronization.getDniType())
                .withName(userSynchronization.getFirstName())
                .withLastName(userSynchronization.getLastName())
                .withEmail(userSynchronization.getEmail())
                .withUsername(userSynchronization.getDni())
                .withPassword(userSynchronization.getDni())
                .withState("A")
                .withRol("USUARIO")
                .build();
    }

    /**
     * Transforms a List<UserSynchronization> to List<UserDTO>
     *
     * @param userSynchronization a list of k to convert
     * @return List<UserDTO> the new object
     */
    @Override
    public List<UserDTO> convert(List<UserSynchronizationDto> userSynchronization) {

        log.debug("User Synchronization process, converting UserSynchronizationList to UserDTOList, " +
                "transforming [{}] records.", userSynchronization.size());
        return userSynchronization.stream().map(this::convert).collect(Collectors.toList());
    }
}
