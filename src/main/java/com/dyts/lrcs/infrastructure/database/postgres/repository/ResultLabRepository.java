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

package com.dyts.lrcs.infrastructure.database.postgres.repository;

import com.dyts.lrcs.infrastructure.database.postgres.entity.ResultLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface to manage crud operations with the database
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:14 p. m.
 * @since 1.0.0
 */
@Repository
public interface ResultLabRepository extends JpaRepository<ResultLab, String> {

    /**
     * search for lab exam result by patient code
     * @param patientCode the patient code to search
     * @return a List of {@link ResultLab} found
     * */
    List<ResultLab> findResultLabByPatientCode(String patientCode);

    /**
     * Count the existence of a patient code
     * @param patientCode the code to search
     * @return 0 if not exists otherwise the quantity found
     * */
    long countByPatientCode(String patientCode);
}
