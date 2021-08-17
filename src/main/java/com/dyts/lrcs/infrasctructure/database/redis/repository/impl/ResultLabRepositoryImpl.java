/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * ResultLabRepositoryImpl.java
 */
package com.dyts.lrcs.infrasctructure.database.redis.repository.impl;

import com.dyts.lrcs.infrasctructure.database.redis.entity.ResultLab;
import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronizationRedis;
import com.dyts.lrcs.infrasctructure.database.redis.repository.api.ResultLabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to implement the database operations for redis for class {@link ResultLab}
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:18 p. m.
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class ResultLabRepositoryImpl implements ResultLabRepository {

    /** the user synchronization key*/
    private static final String TABLE_KEY = "RESULT_LAB";

    /** to access Redis cache */
    private HashOperations redisOperations;

    /** the Redis template for database operations */
    @Qualifier("redisTemplate")
    private final RedisTemplate<String, UserSynchronizationRedis> redisTemplate;

    /**
     * Init the redis operation
     * */
    @PostConstruct
    protected void init() {

        this.redisOperations = redisTemplate.opsForHash();
    }

    /**
     * returns all the data in the database who match the entity T
     *
     * @return a list of object of T
     */
    @Override
    public List<ResultLab> findAll() {

        final var redisMap = findAllMap();

        return new ArrayList<>(redisMap.values());
    }

    /**
     * returns all the data in the database who match the entity T
     *
     * @return a list of object of T
     */
    @Override
    public Map<String, ResultLab> findAllMap() {

        return this.redisOperations.entries(TABLE_KEY);
    }

    /**
     * returns all the data in the database who match the Id of the entity T
     *
     * @param var1 the id to find
     * @return a list of object of T
     */
    @Override
    public List<ResultLab> findAllById(Iterable<String> var1) {

        return findAll();
    }

    /**
     * returns the entity saved
     *
     * @param var1 list of data to persist
     * @return a list of object of T persisted
     */
    @Override
    public <S extends ResultLab> List<S> saveAll(Iterable<S> var1) {

        final var redisHashMap = new HashMap<String, ResultLab>();
        var1.forEach(u -> redisHashMap.put(u.getDni(), u));

        this.redisOperations.putAll(TABLE_KEY, redisHashMap);

        return (List<S>) var1;
    }

    /**
     * return the entity saved
     *
     * @param var1 the data from S to persist
     * @return an object of T persisted
     */
    @Override
    public ResultLab saveAndFlush(ResultLab var1) {

        this.redisOperations.put(TABLE_KEY, var1.getDni(), var1);

        return getOne(var1.getId());
    }

    /**
     * Deletes an object from the database fot the entity T
     *
     * @param var1 the object to remove
     */
    @Override
    public void delete(ResultLab var1) {

        this.redisOperations.delete(TABLE_KEY, var1.getDni());
    }

    /**
     * returns the data in the database who match the ID of the entity T
     *
     * @param var1 the ID of the object to find
     * @return an object of T or null if not found
     */
    @Override
    public ResultLab getOne(String var1) {

        return (ResultLab) this.redisOperations.get(TABLE_KEY, var1);
    }
}
