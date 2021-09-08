/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationManagerRedis.java
 */
package com.dyts.lrcs.managers;

import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.infrasctructure.database.postgres.entity.UserSynchronization;

import java.util.List;

/**
 * Interface to define operations to manager Redis User Synchronization operations
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 12:34 p. m.
 * @since 1.0.0
 */
public interface UserSynchronizationManager {

    /**
     * synchronize user with redis database
     *
     * @param userSynchronizationRedisList the user list to synchronize
     * @return a List<{@link UserSynchronization}> with users synchronized
     */
    List<UserSynchronization> synchronizeUserRedis(List<UserSynchronization> userSynchronizationRedisList);

    /**
     * take the UserSynchronization list get from postgres database and transform in a list of
     * {@link UserSynchronization} to synchronize
     *
     * @param userSynchronizationList an UserSynchronization list with users to synchronize with redis
     * @return a List<{@link UserSynchronization}> with users to synchronize
     * */
    List<UserSynchronization> usersToSynchronize(List<UserSynchronizationDto> userSynchronizationList);

}