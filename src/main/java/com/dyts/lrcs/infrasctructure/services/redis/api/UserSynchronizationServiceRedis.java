/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationServiceRedis.java
 */
package com.dyts.lrcs.infrasctructure.services.redis.api;

import com.dyts.lrcs.infrasctructure.database.entity.redis.UserSynchronizationRedis;

import java.util.List;
import java.util.Map;

/**
 * Interface to define business logic method for the User Synchronization Redis service
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 12:16 p. m.
 * @since 1.0.0
 */
public interface UserSynchronizationServiceRedis {

    /**
     * persist an User to redis database
     * @param user the user to persis
     * */
    void save(UserSynchronizationRedis user);

    /**
     * persist a list of user to redis database
     * @param userSynchronizationList the user to persist
     * @return a list of UserSynchronization
     * */
    List<UserSynchronizationRedis> saveAll(List<UserSynchronizationRedis> userSynchronizationList);

    /**
     * finds all User in redis database
     * @return a map of user with the id as a key
     * */
    List<UserSynchronizationRedis> findAll();

    /**
     * finds all User in redis database
     * @return a map of user with the id as a key
     * */
    Map<String, UserSynchronizationRedis> findAllMap();

    /**
     * finds all User in redis database
     * @param paramList the parameter list to search
     * @return a map of user with the id as a key
     * */
    List<UserSynchronizationRedis> findAllByParameter(List<String> paramList);

    /**
     * finds an User in redis database
     * @param id the id to find
     * @return an object of user synchronization or null if not found
     * */
    UserSynchronizationRedis findById(String id);

    /**
     * update an User in redis database
     * @param user the user to be updated
     * */
    void update(UserSynchronizationRedis user);

    /**
     * remove an User in redis database
     * @param user the id the user to delete
     * */
    void delete(UserSynchronizationRedis user);
}
