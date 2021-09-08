/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-sync
 * ResultLab.java
 */
package com.dyts.lrcs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Class to map the data from the exam result
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 14/08/21 9:19 p. m.
 * @since 1.0.0
 */
@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultLabDto {

    /** The table id */
    private Long id;

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
    private String creationHour;

    /** The patient document type */
    private String documentType;

    /** The patient document identity */
    private String dni;

    /** The patient client code */
    private String patientClientCode;

    /** The exam code of the result */
    private String examCode;

    /** The exam name of the result */
    private String examName;

    /** The balance of the company */
    private Double balance;

    /** The company document number associated to the exam result*/
    private String companyDni;

    /** the code of the exam result */
    private int code;

    /** the analysis code of the exam result */
    private String analysisCode;

    /** the analysis of the patient */
    private String analysis;

    /** the analysis result of the exam*/
    private String results;

    /** the minimum reference value of the exam */
    private String minimum;

    /** the intermediate reference value of the exam */
    private String intermediate;

    /** the maximum reference value of the exam */
    private String maximum;

    /** the unit measure of the exams */
    private String units;

    /** the source of the message*/
    private String source;
}
