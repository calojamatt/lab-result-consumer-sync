/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationManagerRedisImpl.java
 */
package com.dyts.lrcs.managers.impl;

import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.database.postgres.entity.UserSynchronization;
import com.dyts.lrcs.infrasctructure.services.postgres.api.UserSynchronizationService;
import com.dyts.lrcs.managers.UserSynchronizationManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to implement the UserSynchronizationManager operations
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 12:40 p. m.
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserSynchronizationManagerImpl implements UserSynchronizationManager {

    /** the user service for redis */
    private final UserSynchronizationService userSynchronizationService;

    /** the converter for UserSynchronization */
    private final Converter<UserSynchronization, UserSynchronizationDto> userSynchronizationConverter;

    /**
     * take the UserSynchronization list get from postgres database and transform in a list of
     * {@link UserSynchronization} to synchronize
     *
     * @param userSynchronizationDtoList an UserSynchronizationDto list with users to synchronize with redis
     * @return a List<{@link UserSynchronization}> with users to synchronize
     * */
    @Override
    public List<UserSynchronization> usersToSynchronize(List<UserSynchronizationDto> userSynchronizationDtoList) {

        var userSynchronizationRedisList =
                userSynchronizationConverter.convert(userSynchronizationDtoList);

        var usersToSave = userSynchronizationRedisList.stream()
                .map(user -> {
                    var userFound = userSynchronizationService.findById(user.getDni());
                    return Objects.isNull(userFound) ? user : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if ( !usersToSave.isEmpty()) {
            synchronizeUserRedis(usersToSave);
        }
        return usersToSave;
    }

    /**
     * synchronize user with redis database
     *
     * @param userSynchronizationRedisList the user list to synchronize
     * @return a List<{@link UserSynchronization}> with users synchronized
     */
    @Override
    public List<UserSynchronization> synchronizeUserRedis(List<UserSynchronization> userSynchronizationRedisList) {

        try {
            userSynchronizationService.saveAll(userSynchronizationRedisList);
            log.info("User Synchronization process, users synchronized.");
            return userSynchronizationRedisList;
        } catch(Exception e) {
            log.warn("[LAB-RESULT-USER-SYNC-PROCESS] Error trying to insert user list. Detail: {}",
                    e.getMessage());
            return Collections.emptyList();
        }
    }
}
