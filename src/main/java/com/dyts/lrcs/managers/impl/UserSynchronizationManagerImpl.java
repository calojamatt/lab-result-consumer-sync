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
import com.dyts.lrcs.infrastructure.database.dynamodb.services.api.UserSynchronizationServiceDynamoDB;
import com.dyts.lrcs.infrastructure.database.postgres.entity.Users;
import com.dyts.lrcs.infrastructure.database.postgres.services.api.UserService;
import com.dyts.lrcs.infrastructure.database.postgres.services.api.UserSynchronizationService;
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
    private final UserService userService;

    private final UserSynchronizationService userSynchronizationService;

    private final UserSynchronizationServiceDynamoDB userSynchronizationServiceDynamoDB;

    /** the converter for UserSynchronization */
    private final Converter<Users, UserSynchronizationDto> userSynchronizationConverter;

    /** used to transform Users into UsersSynchronization */
    private final Converter<com.dyts.lrcs.infrastructure.database.postgres.entity.UsersSynchronization, Users> synchronizationUsersConverter;

    private final Converter<com.dyts.lrcs.infrastructure.database.dynamodb.datamodel.UsersSynchronization, Users> usersConverterDynamoDb;

    /**
     * take the UserSynchronization list get from postgres database and transform in a list of
     * {@link Users} to synchronize
     *
     * @param userSynchronizationDtoList an UserSynchronizationDto list with users to synchronize with redis
     * @return a List<{@link Users}> with users to synchronize
     * */
    @Override
    public List<Users> usersToSynchronize(List<UserSynchronizationDto> userSynchronizationDtoList) {

        var userSynchronizationList =
                userSynchronizationConverter.convert(userSynchronizationDtoList);

        var usersToSave = userSynchronizationList.stream()
                .map(user -> {
                    var userFound = userService.findById(user.getDni());
                    return Objects.isNull(userFound) ? user : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if ( !usersToSave.isEmpty()) {
            usersToSave = synchronizeUser(usersToSave);
        }
        return usersToSave;
    }

    /**
     * synchronize user with redis database
     *
     * @param usersList the user list to synchronize
     * @return a List<{@link Users}> with users synchronized
     */
    @Override
    public List<Users> synchronizeUser(List<Users> usersList) {

        try {
            userSynchronizationServiceDynamoDB.saveAll(usersConverterDynamoDb.convert(usersList));
            // usersList = userService.saveAll(usersList);
            userSynchronizationService.saveAll(synchronizationUsersConverter.convert(usersList));
            log.info("User Synchronization process, users synchronized.");
            return usersList;
        } catch(Exception e) {
            log.warn("[LAB-RESULT-USER-SYNC-PROCESS] Error trying to insert user list. Detail: {}",
                    e.getMessage());
            return Collections.emptyList();
        }
    }
}
