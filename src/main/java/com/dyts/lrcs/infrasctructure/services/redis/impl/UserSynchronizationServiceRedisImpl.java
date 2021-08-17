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

import com.dyts.lrcs.infrasctructure.database.redis.entity.UserSynchronization;
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
    public void save(UserSynchronization user) {

        userSynchronizationRepositoryRedis.saveAndFlush(user);

    }

    /**
     * persist a list of user to redis database
     *
     * @param userSynchronizationList the user to persis
     * @return a list of UserSynchronization
     */
    @Override
    public List<UserSynchronization> saveAll(List<UserSynchronization> userSynchronizationList) {

        return userSynchronizationRepositoryRedis.saveAll(userSynchronizationList);
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public List<UserSynchronization> findAll() {

        return userSynchronizationRepositoryRedis.findAll();
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public Map<String, UserSynchronization> findAllMap() {

        return userSynchronizationRepositoryRedis.findAllMap();
    }

    /**
     * finds all User in redis database
     *
     * @param paramList the parameter list to search
     * @return a map of user with the id as a key
     */
    @Override
    public List<UserSynchronization> findAllByParameter(List<String> paramList) {

        return userSynchronizationRepositoryRedis.findAllById(paramList);
    }

    /**
     * finds an User in redis database
     *
     * @param id the id to find
     * @return an object of user synchronization or null if not found
     */
    @Override
    public UserSynchronization findById(String id) {

        return userSynchronizationRepositoryRedis.getOne(id);
    }

    /**
     * update an User in redis database
     *
     * @param user the user to be updated
     */
    @Override
    public void update(UserSynchronization user) {

        save(user);
    }

    /**
     * remove an User in redis database
     *
     * @param user the user object to delete
     */
    @Override
    public void delete(UserSynchronization user) {

        userSynchronizationRepositoryRedis.delete(user);
    }
}
