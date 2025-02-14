/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2022
 * All right reserved.
 *
 * lab-result-consumer-sync
 * UserSynchronizationConsumerDelegate
 */
package com.dyts.lrcs.infrastructure.events.consumer.amazonsqs;

import com.dyts.lrcs.dtos.UserSynchronizationDto;
import com.dyts.lrcs.managers.UserSynchronizationManager;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Use the message consumer to read from amazon sqs and process it
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 5/05/22 21
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserSynchronizationConsumerListener {

    @Value(value = "${lab-result.sync.source:DEMO}")
    private String locationSource;

    /** The user synchronization manager to sync data*/
    private final UserSynchronizationManager userSynchronizationManager;

    @SqsListener(value = "${lab-result.user-sync.topic.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void readMessages(String message) {

        final UserSynchronizationDto userSynchronizationDto = convert(message);

        log.debug("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] processing messages received from Amazon SQS. " +
                "Messages to process [{}], {}", message, locationSource);

        if (locationSource.equals(userSynchronizationDto.getSource())) {
            userSynchronizationManager.usersToSynchronize(Collections.singletonList(userSynchronizationDto));
            log.debug("[LAB-RESULT-USER-SYNC-PROCESS-CONSUMER] messages received from Amazon SQS. " +
                    "Messages processed [{}]", message);
        }


    }

    private UserSynchronizationDto convert(String message) {

        return new Gson().fromJson(message, UserSynchronizationDto.class);
    }
}
