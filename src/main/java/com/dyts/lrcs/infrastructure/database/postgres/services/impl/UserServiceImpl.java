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
import com.dyts.lrcs.infrastructure.database.postgres.entity.UsersOldSchema;
import com.dyts.lrcs.infrastructure.database.postgres.repository.UserOldSchemaRepository;
import com.dyts.lrcs.infrastructure.database.postgres.repository.UserRepository;
import com.dyts.lrcs.infrastructure.database.postgres.services.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class to implement the business logic of the redis UserSynchronization
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 12:22 p. m.
 * @since 1.0.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    /** the user repository */
    private final UserRepository userRepository;

    /** to manage the user operation in the old table */
    private final UserOldSchemaRepository userOldSchemaRepository;

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

        // TODO remove when implementing new synchronization
        final Date currentDate = new Date();
        List<UsersOldSchema> usersOldSchemas = usersList.stream().map(users -> UsersOldSchema.builder()
                .withPassword(users.getPassword())
                .withUsername(users.getUsername())
                .withDocumentType(users.getDocumentType())
                .withName(users.getName())
                .withLastName(users.getLastName())
                .withEmail(users.getEmail())
                .withState(users.getState())
                .withRol(users.getRol())
                .withCreateTime(new Timestamp(currentDate.getTime()))
                .build()).collect(Collectors.toList());

        usersOldSchemas = userOldSchemaRepository.saveAll(usersOldSchemas);
        log.info("Users Synchronized in the old table, inserted rows {}", usersOldSchemas.size());

        return userRepository.saveAll(usersList);
    }

    /**
     * finds all User in redis database
     *
     * @return a list of user with the id as a key
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
     * @return a list of user with the id as a key
     */
    @Override
    public List<Users> findAllByParameter(List<String> paramList) {

        return userRepository.findAllById(paramList);
    }

    /**
     * finds a User in redis database
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
