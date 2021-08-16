/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * RedisRepository.java
 */
package com.dyts.lrcs.infrasctructure.database.repository.redis.api;

import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Map;

/**
 * Class to define default operations for redis interface repository
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 10:52 a. m.
 * @since 1.0.0
 */
public interface RedisRepository<T, ID> {

    /**
     * returns all the data in the database who match the entity T
     *
     * @return a list of object of T
     */
    List<T> findAll();

    /**
     * returns all the data in the database who match the entity T
     * @return a list of object of T
     * */
    Map<String, T> findAllMap();

    /**
     * returns all the data in the database who match the Id of the entity T
     * @return a list of object of T
     * */
    List<T> findAllById(Iterable<ID> var1);

    /**
     * returns the entity saved
     * @param var1 list of data to persist
     * @return a list of object of T persisted
     * */
    <S extends T> List<S> saveAll(Iterable<S> var1);

    /**
     * clear the database cache of the entity T
     * */
    void flush();

    /**
     * return the entity saved
     * @param var1 the data from S to persist
     * @return an object of T persisted
     * */
    T saveAndFlush(T var1);

    /**
     * Deletes all object who match with the param list
     * @param var1 a list of object of T to delete massively
     * */
    void deleteInBatch(Iterable<T> var1);

    /**
     * Deletes all object from the database fot the entity T
     * */
    void deleteAllInBatch();

    /**
     * Deletes an object from the database fot the entity T
     * @param var1 the object to remove
     * */
    void delete(T var1);

    /**
     * returns the data in the database who match the ID of the entity T
     * @param var1 the ID of the object to find
     * @return an object of T or null if not found
     * */
    T getOne(ID var1);

    /**
     * returns all the data in the database who match the entity S
     * @return a list of object of S
     * */
    <S extends T> List<S> findAll(Example<S> var1);

}
