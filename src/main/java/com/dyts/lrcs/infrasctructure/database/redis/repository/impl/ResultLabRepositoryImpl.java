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
import com.dyts.lrcs.infrasctructure.database.redis.repository.api.ResultLabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
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

    /** to access Redis cache */
    private ListOperations<String, ResultLab> redisOperations;

    /** the Redis template for database operations */
    private final RedisTemplate<String, ResultLab> redisTemplate;

    /**
     * Init the redis operation
     * */
    @PostConstruct
    protected void init() {

        this.redisOperations = redisTemplate.opsForList();
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

        return null;
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

        var1.forEach(u -> this.redisOperations.leftPush(u.getPatientCode(), u));

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

        this.redisOperations.leftPush(var1.getPatientCode(), var1);
        return getOne(var1.getId());
    }

    /**
     * Deletes an object from the database fot the entity T
     *
     * @param var1 the object to remove
     */
    @Override
    public void delete(ResultLab var1) {

        this.redisOperations.leftPop(var1.getDni());
    }

    /**
     * returns the data in the database who match the ID of the entity T
     *
     * @param var1 the ID of the object to find
     * @return an object of T or null if not found
     */
    @Override
    public ResultLab getOne(String var1) {

        return this.redisOperations.rightPop(var1);
    }
}
