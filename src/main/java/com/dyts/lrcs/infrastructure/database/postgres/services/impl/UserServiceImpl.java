/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserServiceImpl.java
 */
package com.dyts.lrcs.infrastructure.database.postgres.services.impl;

import com.dyts.lrcs.infrastructure.database.postgres.entity.Users;
import com.dyts.lrcs.infrastructure.database.postgres.repository.UserRepository;
import com.dyts.lrcs.infrastructure.database.postgres.services.api.UserService;
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
public class UserServiceImpl implements UserService {

    /** the user repository */
    private final UserRepository userRepository;

    /**
     * persist an User to redis database
     *
     * @param user the user to persis
     */
    @Override
    public void save(Users user) {

        userRepository.saveAndFlush(user);

    }

    /**
     * persist a list of user to redis database
     *
     * @param usersList the user to persis
     * @return a list of UserSynchronization
     */
    @Override
    public List<Users> saveAll(List<Users> usersList) {

        return userRepository.saveAll(usersList);
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public List<Users> findAll() {

        return userRepository.findAll();
    }

    /**
     * finds all User in redis database
     *
     * @return a map of user with the id as a key
     */
    @Override
    public Map<String, Users> findAllMap() {

        return null;
    }

    /**
     * finds all User in redis database
     *
     * @param paramList the parameter list to search
     * @return a map of user with the id as a key
     */
    @Override
    public List<Users> findAllByParameter(List<String> paramList) {

        return userRepository.findAllById(paramList);
    }

    /**
     * finds an User in redis database
     *
     * @param id the id to find
     * @return an object of user synchronization or null if not found
     */
    @Override
    public Users findById(String id) {

        return userRepository.findById(id).orElse(null);
    }

    /**
     * update an User in redis database
     *
     * @param user the user to be updated
     */
    @Override
    public void update(Users user) {

        save(user);
    }

    /**
     * remove an User in redis database
     *
     * @param user the user object to delete
     */
    @Override
    public void delete(Users user) {

        userRepository.delete(user);
    }
}
