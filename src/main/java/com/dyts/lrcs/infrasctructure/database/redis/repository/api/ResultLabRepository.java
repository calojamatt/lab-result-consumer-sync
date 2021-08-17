/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * ResultLab.java
 */

package com.dyts.lrcs.infrasctructure.database.redis.repository.api;

import com.dyts.lrcs.infrasctructure.database.redis.entity.ResultLab;

/**
 * Interface to manage crud operations with the database
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:14 p. m.
 * @since 1.0.0
 */
public interface ResultLabRepository extends RedisRepository<ResultLab, String> {
}
