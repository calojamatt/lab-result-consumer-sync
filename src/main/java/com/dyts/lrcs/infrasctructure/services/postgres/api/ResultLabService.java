/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-consumer-sync
 * ResultLabService.java
 */
package com.dyts.lrcs.infrasctructure.services.postgres.api;



import com.dyts.lrcs.infrasctructure.database.postgres.entity.ResultLab;

import java.util.List;

/**
 * Interface to define business logic method for the labs exams Redis service
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:07 p. m.
 * @since 1.0.0
 */
public interface ResultLabService {

    /**
     * persist an exam result to redis database
     * @param resultLab the exam result to persist
     * */
    void save(ResultLab resultLab);

    /**
     * persist a list of exams results to redis database
     * @param resultLabList the user to persist
     * @return a list of ResultLab
     * */
    List<ResultLab> saveAll(List<ResultLab> resultLabList);

    /**
     * finds all exams in redis database
     * @return a list of exams
     * */
    List<ResultLab> findAll();


    /**
     * finds all exams in redis database
     * @param paramList the parameter list to search
     * @return a list of exams
     * */
    List<ResultLab> findAllByParameter(List<String> paramList);

    /**
     * finds an exam in redis database
     * @param id the id to find
     * @return an object of result lab or null if not found
     * */
    ResultLab findById(String id);

    /**
     * finds an exam in redis database
     * @param patientCode the id to find
     * @return a list of result lab or null if not found
     * */
    List<ResultLab> findByPatientCode(String patientCode);

    /**
     * update an exam in redis database
     * @param resultLab the exam to be updated
     * */
    void update(ResultLab resultLab);

    /**
     * remove an exam in redis database
     * @param resultLab the object of the exam to delete
     * */
    void delete(ResultLab resultLab);

    /**
     * remove an exam in redis database
     * @param patientCode the patientCode of the exam to search
     * @return 0 if patient exists otherwise 1
     * */
    long countByPatientCode(String patientCode);
}
