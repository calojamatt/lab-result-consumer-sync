/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2022
 * All right reserved.
 *
 * lab-result-consumer-sync
 * UserSynchronizationServiceDynamoDB
 */
package com.dyts.lrcs.infrastructure.database.dynamodb.services.api;

import com.dyts.lrcs.infrastructure.database.dynamodb.datamodel.UsersSynchronization;

import java.util.List;

/**
 * For // TODO
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 6/05/22 00
 * @since 1.0.0
 */
public interface UserSynchronizationServiceDynamoDB {

    UsersSynchronization save(UsersSynchronization usersSynchronization);

    List<UsersSynchronization> saveAll(List<UsersSynchronization> usersSynchronizationList);

    UsersSynchronization findByUserDni(String userDni);
}
