/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationServiceImpl.java
 */
package com.dyts.lrcs.infrastructure.database.postgres.services.impl;

import com.dyts.lrcs.infrastructure.database.postgres.entity.UsersSynchronization;
import com.dyts.lrcs.infrastructure.database.postgres.repository.UserSynchronizationRepository;
import com.dyts.lrcs.infrastructure.database.postgres.services.api.UserSynchronizationService;
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
public class UsersSynchronizationServiceImpl implements UserSynchronizationService {

    /** the user synchronization repository */
    private final UserSynchronizationRepository userSynchronizationRepository;

    /**
     * persist an User to redis database
     *
     * @param user the user to persis
     */
    @Override
    public void save(UsersSynchronization user) {

        userSynchronizationRepository.saveAndFlush(user);

    }

    /**
     * persist a list of user to redis database
     *
     * @param usersList the user to persis
     * @return a list of UserSynchronization
     */
    @Override
    public List<UsersSynchronization> saveAll(List<UsersSynchronization> usersList) {

        usersList.forEach(usersSynchronization -> usersSynchronization.setState("SYNCHRONIZED"));

        return userSynchronizationRepository.saveAll(usersList);
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public List<UsersSynchronization> findAll() {

        return userSynchronizationRepository.findAll();
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public Map<String, UsersSynchronization> findAllMap() {

        return null;
    }

    /**
     * finds all User in redis database
     *
     * @param paramList the parameter list to search
     * @return a map of user with the id as a key
     */
    @Override
    public List<UsersSynchronization> findAllByParameter(List<String> paramList) {

        return userSynchronizationRepository.findAllById(paramList);
    }

    /**
     * finds an User in redis database
     *
     * @param id the id to find
     * @return an object of user synchronization or null if not found
     */
    @Override
    public UsersSynchronization findById(String id) {

        return userSynchronizationRepository.findById(id).orElse(null);
    }

    /**
     * update an User in redis database
     *
     * @param user the user to be updated
     */
    @Override
    public void update(UsersSynchronization user) {

        save(user);
    }

    /**
     * remove an User in redis database
     *
     * @param user the user object to delete
     */
    @Override
    public void delete(UsersSynchronization user) {

        userSynchronizationRepository.delete(user);
    }
}
