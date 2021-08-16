/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationManager.java
 */
package com.dyts.lrcs.managers;

import com.dyts.lrcs.managers.api.UserSynchronizationManagerRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class to complete the user synchronization process
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 20/06/21 11:43 p. m.
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserSynchronizationManager {

    /** The user synchronization topic name */
    @Value(value = "${lab-result.user-sync.topic.name}")
    private String labResultUserSyncTopicName;

    /** user synchronization user manager for redis */
    private final UserSynchronizationManagerRedis userSynchronizationManagerRedis;



    /**
     * Receives a list of user to synchronize and inserts it to redis and publish a message to kafka
     *
     * */
    public void synchronizeUserRedis() {

        log.debug("[LAB-RESULT-USER-SYNC-PROCESS] starting to synchronize new users");
        /*final var userSynchronizationRedisList =
                userSynchronizationManagerRedis.usersToSynchronize(
                        userSynchronizationValidator.validateInvalidUsers(userSynchronizationService.findUsers()));*/

        //log.debug("[LAB-RESULT-USER-SYNC-PROCESS] new users to synchronize [{}]", userSynchronizationRedisList.size());
        /*final var userSynchronizationList =
                userSynchronizationManagerRedis.synchronizeUserRedis(userSynchronizationRedisList);*/

        //log.debug("[LAB-RESULT-SYNC-PROCESS] starting publishing message to topic: [{}]", labResultUserSyncTopicName);
        /*userSynchronizationList.forEach(userSynchronization -> messageProducerManager.sendMessage(
                labResultUserSyncTopicName, userSynchronization.getDni(), JsonConverterImpl.toJson(userSynchronization)))*/
    }
}
