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
package com.dyts.lrcs.infrasctructure.database.repository.redis.impl;

import com.dyts.lrcs.infrasctructure.database.entity.redis.UserSynchronizationRedis;
import com.dyts.lrcs.infrasctructure.database.repository.redis.api.UserSynchronizationRepositoryRedis;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class to implement the database operations for redis for class UserSynchronization
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 11:00 a. m.
 * @since 1.0.0
 */
@Repository
public class UserSynchronizationRepositoryRedisImpl implements UserSynchronizationRepositoryRedis {

    /** the user synchronization key*/
    private static final String USER_SYNCHRONIZATION_KEY = "USER_SYNCHRONIZATION";

    /** to access Redis cache */
    private final HashOperations redisOperations;

    /** the repository constructor */
    public UserSynchronizationRepositoryRedisImpl(RedisTemplate<String, UserSynchronizationRedis> redisTemplate) {

        this.redisOperations = redisTemplate.opsForHash();
    }

    /**
     * returns all the data in the database who match the entity T
     *
     * @return a list of object of T
     */
    @Override
    public List<UserSynchronizationRedis> findAll() {

        final var redisMap = findAllMap();

        return new ArrayList<>(redisMap.values());
    }

    /**
     * returns all the data in the database who match the entity T
     *
     * @return a list of object of T
     */
    @Override
    public Map<String, UserSynchronizationRedis> findAllMap() {

        return this.redisOperations.entries(USER_SYNCHRONIZATION_KEY);
    }

    /**
     * returns all the data in the database who match the Id of the entity T
     *
     * @param var1 a list of String with id
     * @return a list of object of T
     */
    @Override
    public List<UserSynchronizationRedis> findAllById(Iterable<String> var1) {

        return findAll().stream()
                .filter(userSynchronizationRedis -> userSynchronizationRedis.getStatus().equals("NOT_SYNCHRONIZED"))
                .collect(Collectors.toList());
    }

    /**
     * returns the entity saved
     *
     * @param var1 list of data to persist
     * @return a list of object of T persisted
     */
    @Override
    public <S extends UserSynchronizationRedis> List<S> saveAll(Iterable<S> var1) {

        final var userSynchronizationMap = new HashMap<String, UserSynchronizationRedis>();
        var1.forEach(u -> userSynchronizationMap.put(u.getDni(), u));

        this.redisOperations.putAll(USER_SYNCHRONIZATION_KEY, userSynchronizationMap);

        return (List<S>) var1;
    }

    /**
     * clear the database cache of the entity T
     */
    @Override
    public void flush() {

    }

    /**
     * return the entity saved
     *
     * @param var1 the data from S to persist
     * @return an object of T persisted
     */
    @Override
    public UserSynchronizationRedis saveAndFlush(UserSynchronizationRedis var1) {

        this.redisOperations.put(USER_SYNCHRONIZATION_KEY, var1.getDni(), var1);

        return getOne(var1.getDni());
    }

    /**
     * Deletes all object who match with the param list
     *
     * @param var1 a list of object of T to delete massively
     */
    @Override
    public void deleteInBatch(Iterable<UserSynchronizationRedis> var1) {

    }

    /**
     * Deletes all object from the database fot the entity T
     */
    @Override
    public void deleteAllInBatch() {

    }

    /**
     * Deletes an object from the database fot the entity T
     *
     * @param userSynchronization the id to remove
     */
    @Override
    public void delete(UserSynchronizationRedis userSynchronization) {

        this.redisOperations.delete(USER_SYNCHRONIZATION_KEY, userSynchronization.getDni());
    }

    /**
     * returns the data in the database who match the ID of the entity T
     *
     * @param var1 the ID of the object to find
     * @return an object of T or null if not found
     */
    @Override
    public UserSynchronizationRedis getOne(String var1) {

        return (UserSynchronizationRedis) this.redisOperations.get(USER_SYNCHRONIZATION_KEY, var1);
    }

    /**
     * returns all the data in the database who match the entity S
     *
     * @param var1 an entity
     * @return a list of object of S
     */
    @Override
    public <S extends UserSynchronizationRedis> List<S> findAll(Example<S> var1) {

        return Collections.emptyList();
    }
}
