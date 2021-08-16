/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * ResultLab.java
 */
package com.dyts.lrcs.infrasctructure.services.redis.impl;

import com.dyts.lrcs.infrasctructure.database.redis.entity.ResultLab;
import com.dyts.lrcs.infrasctructure.database.redis.repository.api.ResultLabRepository;
import com.dyts.lrcs.infrasctructure.services.redis.api.ResultLabService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to manage the operations of the database and control the business logic
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 16/08/21 4:12 p. m.
 * @since 1.0.0
 */
@AllArgsConstructor
@Service
public class ResultLabServiceImpl implements ResultLabService {

    /**
     * the repository to manage the database information
     */
    private final ResultLabRepository resultLabRepository;

    /**
     * persist an exam result to redis database
     *
     * @param resultLab the exam result to persist
     */
    @Override
    public void save(ResultLab resultLab) {

        resultLabRepository.save(resultLab);
    }

    /**
     * persist a list of exams results to redis database
     *
     * @param resultLabList the user to persist
     * @return a list of ResultLab
     */
    @Override
    public List<ResultLab> saveAll(List<ResultLab> resultLabList) {

        return (List<ResultLab>) resultLabRepository.saveAll(resultLabList);
    }

    /**
     * finds all exams in redis database
     *
     * @return a list of exams
     */
    @Override
    public List<ResultLab> findAll() {

        return (List<ResultLab>) resultLabRepository.findAll();
    }

    /**
     * finds all exams in redis database
     *
     * @param paramList the parameter list to search
     * @return a list of exams
     */
    @Override
    public List<ResultLab> findAllByParameter(List<String> paramList) {

        return (List<ResultLab>) resultLabRepository.findAllById(paramList);
    }

    /**
     * finds an exam in redis database
     *
     * @param id the id to find
     * @return an object of result lab or null if not found
     */
    @Override
    public ResultLab findById(String id) {

        return resultLabRepository.findById(id).orElse(null);
    }

    /**
     * update an exam in redis database
     *
     * @param resultLab the exam to be updated
     */
    @Override
    public void update(ResultLab resultLab) {

        resultLabRepository.save(resultLab);
    }

    /**
     * remove an exam in redis database
     *
     * @param resultLab the object of the exam to delete
     */
    @Override
    public void delete(ResultLab resultLab) {

        resultLabRepository.delete(resultLab);
    }
}
