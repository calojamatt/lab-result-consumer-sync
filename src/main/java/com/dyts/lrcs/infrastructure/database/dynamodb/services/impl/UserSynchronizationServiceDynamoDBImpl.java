/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2022
 * All right reserved.
 *
 * lab-result-consumer-sync
 * UserSynchronizationServiceDynamoDBImpl
 */
package com.dyts.lrcs.infrastructure.database.dynamodb.services.impl;

import com.dyts.lrcs.infrastructure.database.dynamodb.datamodel.UsersSynchronization;
import com.dyts.lrcs.infrastructure.database.dynamodb.repository.UsersSynchronizationRepositoryDynamoDB;
import com.dyts.lrcs.infrastructure.database.dynamodb.services.api.UserSynchronizationServiceDynamoDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class for // TODO
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 6/05/22 00
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class UserSynchronizationServiceDynamoDBImpl implements UserSynchronizationServiceDynamoDB {

    private final UsersSynchronizationRepositoryDynamoDB usersSynchronizationRepositoryDynamoDB;

    @Override
    public UsersSynchronization save(UsersSynchronization usersSynchronization) {

        return usersSynchronizationRepositoryDynamoDB.save(usersSynchronization);
    }

    @Override
    public List<UsersSynchronization> saveAll(List<UsersSynchronization> usersSynchronizationList) {

        return (List<UsersSynchronization>) usersSynchronizationRepositoryDynamoDB.saveAll(usersSynchronizationList);
    }

    @Override
    public UsersSynchronization findByUserDni(String userDni) {

        return usersSynchronizationRepositoryDynamoDB.findUserByDni(userDni);
    }
}
