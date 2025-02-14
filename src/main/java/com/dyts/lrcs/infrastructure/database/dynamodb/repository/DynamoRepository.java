/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2022
 * All right reserved.
 *
 * lab-result-consumer-sync
 * DynamoRepository
 */
package com.dyts.lrcs.infrastructure.database.dynamodb.repository;

import java.util.Optional;

/**
 * Define the funcionality of the dynamoDb database operations
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 5/05/22 23
 * @since 1.0.0
 */
public interface DynamoRepository<T, ID> {

    <S extends T> S save(S var1);

    <S extends T> Iterable<S> saveAll(Iterable<S> var1);

    Optional<T> findById(ID var1);

    boolean existsById(ID var1);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> var1);

    long count();

    void deleteById(ID var1);

    void delete(T var1);

    void deleteAll(Iterable<? extends T> var1);

    void deleteAll();

}
