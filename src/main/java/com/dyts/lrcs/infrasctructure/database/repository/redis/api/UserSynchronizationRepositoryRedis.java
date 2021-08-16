/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationRedis.java
 */
package com.dyts.lrcs.infrasctructure.database.repository.redis.api;

import com.dyts.lrcs.infrasctructure.database.entity.redis.UserSynchronizationRedis;

/**
 * Interface to define the operations for redis user synchronization
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 10:51 a. m.
 * @since 1.0.0
 */
public interface UserSynchronizationRepositoryRedis extends RedisRepository<UserSynchronizationRedis, String> {
}
