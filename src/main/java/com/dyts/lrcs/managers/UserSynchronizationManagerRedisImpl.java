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
package com.dyts.lrcs.managers;

import com.dyts.lrcs.converters.api.Converter;
import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.database.entity.redis.UserSynchronizationRedis;
import com.dyts.lrcs.infrasctructure.services.redis.api.UserSynchronizationServiceRedis;
import com.dyts.lrcs.managers.api.UserSynchronizationManagerRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to implement the UserSynchronizationManager operations
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 12:40 p. m.
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class UserSynchronizationManagerRedisImpl implements UserSynchronizationManagerRedis {

    /** the user service for redis */
    private final UserSynchronizationServiceRedis userSynchronizationServiceRedis;

    /** the converter for UserSynchronizationRedis */
    private final Converter<UserSynchronizationRedis, UserSynchronizationDto> userSynchronizationConverter;

    /**
     * synchronize user with redis database
     *
     * @param userSynchronizationRedisList the user list to synchronize
     * @return a List<{@link UserSynchronizationRedis}> with users synchronized
     */
    @Override
    public List<UserSynchronizationRedis> synchronizeUserRedis(
            List<UserSynchronizationRedis> userSynchronizationRedisList) {

        userSynchronizationServiceRedis.saveAll(userSynchronizationRedisList);

        return userSynchronizationServiceRedis.findAllByParameter(Collections.singletonList("NOT_SYNCHRONIZED"));
    }

    /**
     * take the UserSynchronization list get from postgres database and transform in a list of
     * {@link UserSynchronizationRedis} to synchronize
     *
     * @param userSynchronizationList an UserSynchronization list with users to synchronize with redis
     * @return a List<{@link UserSynchronizationRedis}> with users to synchronize
     * */
    public List<UserSynchronizationRedis> usersToSynchronize(List<UserSynchronizationDto> userSynchronizationList) {

        var userSynchronizationRedisList =
                userSynchronizationConverter.convert(userSynchronizationList);
        updateUserSynchronizationStatus(userSynchronizationRedisList);

        var synchronizationMap = userSynchronizationServiceRedis.findAllMap();

        userSynchronizationRedisList = userSynchronizationRedisList.stream()
                .filter(userSynchronizationRedis -> !synchronizationMap.containsKey(userSynchronizationRedis.getDni()))
                .collect(Collectors.toList());

        return userSynchronizationRedisList;
    }

    /**
     * Modified the user in redis after publish the message in kafka
     * @param userSynchronizationRedis the user object to update
     */
    @Override
    public void updateSynchronizedUser(UserSynchronizationRedis userSynchronizationRedis) {

        userSynchronizationRedis.setStatus("SYNCHRONIZED");
        userSynchronizationServiceRedis.update(userSynchronizationRedis);
    }

    /***/
    private void updateUserSynchronizationStatus(List<UserSynchronizationRedis> userSynchronizationRedisList) {

        userSynchronizationRedisList.forEach(userSynchronizationRedis -> userSynchronizationRedis.setStatus("NOT_SYNCHRONIZED"));
    }
}
