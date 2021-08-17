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
package com.dyts.lrcs.infrasctructure.database.redis.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * Model Class to manage the exams data for redis
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 16/08/21 4:02 p. m.
 * @since 1.0.0
 */
@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("RESULT_LAB")
public class ResultLab implements Serializable {

    /** The table id */
    private String id;

    /** The patient code of the lab result */
    private String patientCode;

    /** The first name of the patient */
    private String firstName;

    /** The last name of the patient*/
    private String lastName;

    /** The full name of the patient */
    private String fullName;

    /** The creation date of the exam */
    private Date creationDate;

    /** The creation hour of the exam */
    private LocalTime creationHour;

    private String documentType;

    /** The patient document identity */
    private String dni;

    /** The patient clte code */
    private String patientClteCode;

    /** The exam code of the result */
    private String examCode;

    /** The exam name of the result */
    private String examName;

    /** The balance of the company */
    private Double balance;

    /** The company document number associated to the exam result*/
    private String companyDni;

    /** the source of the message*/
    private String source;
}
