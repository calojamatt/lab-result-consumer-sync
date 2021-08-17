/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationRepositoryRedisImpl.java
 */
package com.dyts.lrcs.infrasctructure.database.redis.repository.impl;

import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronization;
import com.dyts.lrcs.infrasctructure.database.redis.repository.api.UserSynchronizationRepositoryRedis;
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
import java.util.stream.Collectors;

/**
 * Class to implement the database operations for redis for class UserSynchronization
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 11:00 a. m.
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository
public class UserSynchronizationRepositoryRedisImpl implements UserSynchronizationRepositoryRedis {

    /** the user synchronization key*/
    private static final String USER_SYNCHRONIZATION_KEY = "USERS";

    /** to access Redis cache */
    private HashOperations redisOperations;

    /** the Redis template for database operations */
    @Qualifier("redisTemplate")
    private final RedisTemplate<String, UserSynchronization> redisTemplate;

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
    public List<UserSynchronization> findAll() {

        final var redisMap = findAllMap();

        return new ArrayList<>(redisMap.values());
    }

    /**
     * returns all the data in the database who match the entity T
     *
     * @return a list of object of T
     */
    @Override
    public Map<String, UserSynchronization> findAllMap() {

        return this.redisOperations.entries(USER_SYNCHRONIZATION_KEY);
    }

    /**
     * returns all the data in the database who match the Id of the entity T
     *
     * @param var1 a list of String with id
     * @return a list of object of T
     */
    @Override
    public List<UserSynchronization> findAllById(Iterable<String> var1) {

        return findAll().stream()
                .filter(userSynchronizationRedis -> userSynchronizationRedis.getState().equals("NOT_SYNCHRONIZED"))
                .collect(Collectors.toList());
    }

    /**
     * returns the entity saved
     *
     * @param var1 list of data to persist
     * @return a list of object of T persisted
     */
    @Override
    public <S extends UserSynchronization> List<S> saveAll(Iterable<S> var1) {

        final var userSynchronizationMap = new HashMap<String, UserSynchronization>();
        var1.forEach(u -> userSynchronizationMap.put(u.getDni(), u));

        this.redisOperations.putAll(USER_SYNCHRONIZATION_KEY, userSynchronizationMap);

        return (List<S>) var1;
    }


    /**
     * return the entity saved
     *
     * @param var1 the data from S to persist
     * @return an object of T persisted
     */
    @Override
    public UserSynchronization saveAndFlush(UserSynchronization var1) {

        this.redisOperations.put(USER_SYNCHRONIZATION_KEY, var1.getDni(), var1);

        return getOne(var1.getDni());
    }

    /**
     * Deletes an object from the database fot the entity T
     *
     * @param userSynchronization the id to remove
     */
    @Override
    public void delete(UserSynchronization userSynchronization) {

        this.redisOperations.delete(USER_SYNCHRONIZATION_KEY, userSynchronization.getDni());
    }

    /**
     * returns the data in the database who match the ID of the entity T
     *
     * @param var1 the ID of the object to find
     * @return an object of T or null if not found
     */
    @Override
    public UserSynchronization getOne(String var1) {

        return (UserSynchronization) this.redisOperations.get(USER_SYNCHRONIZATION_KEY, var1);
    }
}
