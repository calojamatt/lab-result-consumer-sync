/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationServiceRedisImpl.java
 */
package com.dyts.lrcs.infrasctructure.services.redis.impl;

import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronizationRedis;
import com.dyts.lrcs.infrasctructure.database.redis.repository.api.UserSynchronizationRepositoryRedis;
import com.dyts.lrcs.infrasctructure.services.redis.api.UserSynchronizationServiceRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Class to implement the business logic of the redis UserSynchronization
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 12:22 p. m.
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Service
public class UserSynchronizationServiceRedisImpl implements UserSynchronizationServiceRedis {

    /** the redis user synchronization repository */
    private final UserSynchronizationRepositoryRedis userSynchronizationRepositoryRedis;

    /**
     * persist an User to redis database
     *
     * @param user the user to persis
     */
    @Override
    public void save(UserSynchronizationRedis user) {

        userSynchronizationRepositoryRedis.saveAndFlush(user);

    }

    /**
     * persist a list of user to redis database
     *
     * @param userSynchronizationList the user to persis
     * @return a list of UserSynchronization
     */
    @Override
    public List<UserSynchronizationRedis> saveAll(List<UserSynchronizationRedis> userSynchronizationList) {

        return userSynchronizationRepositoryRedis.saveAll(userSynchronizationList);
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public List<UserSynchronizationRedis> findAll() {

        return userSynchronizationRepositoryRedis.findAll();
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public Map<String, UserSynchronizationRedis> findAllMap() {

        return userSynchronizationRepositoryRedis.findAllMap();
    }

    /**
     * finds all User in redis database
     *
     * @param paramList the parameter list to search
     * @return a map of user with the id as a key
     */
    @Override
    public List<UserSynchronizationRedis> findAllByParameter(List<String> paramList) {

        return userSynchronizationRepositoryRedis.findAllById(paramList);
    }

    /**
     * finds an User in redis database
     *
     * @param id the id to find
     * @return an object of user synchronization or null if not found
     */
    @Override
    public UserSynchronizationRedis findById(String id) {

        return userSynchronizationRepositoryRedis.getOne(id);
    }

    /**
     * update an User in redis database
     *
     * @param user the user to be updated
     */
    @Override
    public void update(UserSynchronizationRedis user) {

        save(user);
    }

    /**
     * remove an User in redis database
     *
     * @param user the user object to delete
     */
    @Override
    public void delete(UserSynchronizationRedis user) {

        userSynchronizationRepositoryRedis.delete(user);
    }
}
