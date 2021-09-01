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
package com.dyts.lrcs.infrasctructure.services.postgres.impl;

import com.dyts.lrcs.infrasctructure.database.postgres.entity.UserSynchronization;
import com.dyts.lrcs.infrasctructure.database.postgres.repository.UserSynchronizationRepository;
import com.dyts.lrcs.infrasctructure.services.postgres.api.UserSynchronizationService;
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
public class UserSynchronizationServiceImpl implements UserSynchronizationService {

    /** the redis user synchronization repository */
    private final UserSynchronizationRepository userSynchronizationRepository;

    /**
     * persist an User to redis database
     *
     * @param user the user to persis
     */
    @Override
    public void save(UserSynchronization user) {

        userSynchronizationRepository.saveAndFlush(user);

    }

    /**
     * persist a list of user to redis database
     *
     * @param userSynchronizationList the user to persis
     * @return a list of UserSynchronization
     */
    @Override
    public List<UserSynchronization> saveAll(List<UserSynchronization> userSynchronizationList) {

        return userSynchronizationRepository.saveAll(userSynchronizationList);
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public List<UserSynchronization> findAll() {

        return userSynchronizationRepository.findAll();
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public Map<String, UserSynchronization> findAllMap() {

        return null;
    }

    /**
     * finds all User in redis database
     *
     * @param paramList the parameter list to search
     * @return a map of user with the id as a key
     */
    @Override
    public List<UserSynchronization> findAllByParameter(List<String> paramList) {

        return userSynchronizationRepository.findAllById(paramList);
    }

    /**
     * finds an User in redis database
     *
     * @param id the id to find
     * @return an object of user synchronization or null if not found
     */
    @Override
    public UserSynchronization findById(String id) {

        return userSynchronizationRepository.getOne(id);
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

        userSynchronizationRepository.delete(user);
    }
}
