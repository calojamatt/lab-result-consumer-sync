/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2022
 * All right reserved.
 *
 * lab-result-consumer-sync
 * UsersSynchronizationRepositoryDynamoDB
 */
package com.dyts.lrcs.infrastructure.database.dynamodb.repository;

import com.dyts.lrcs.infrastructure.database.dynamodb.datamodel.UsersSynchronization;

/**
 * Interface Repository to perform operations with database
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 5/05/22 23
 * @since 1.0.0
 */
public interface UsersSynchronizationRepositoryDynamoDB extends DynamoRepository<UsersSynchronization, String> {

    UsersSynchronization findUserByDni(String dni);
}
