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
package com.dyts.lrcs.infrasctructure.database.postgres.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "result_lab")
public class ResultLab implements Serializable {

    /** The table id */
    @Id
    private String id;

    /** The patient code of the lab result */
    @Column(name = "patient_code")
    private String patientCode;

    /** The first name of the patient */
    @Column(name = "first_name")
    private String firstName;

    /** The last name of the patient*/
    @Column(name = "last_name")
    private String lastName;

    /** The full name of the patient */
    @Column(name = "full_name")
    private String fullName;

    /** The creation date of the exam */
    @Column(name = "creation_date")
    private Date creationDate;

    /** The creation hour of the exam */
    @Column(name = "creation_hour")
    private LocalTime creationHour;

    private String documentType;

    /** The patient document identity */
    @Column(name = "dni")
    private String dni;

    /** The patient clte code */
    @Column(name = "patient_client_code")
    private String patientClientCode;

    /** The exam code of the result */
    @Column(name = "exam_code")
    private String examCode;

    /** The exam name of the result */
    @Column(name = "exam_name")
    private String examName;

    /** The balance of the company */
    @Column(name = "balance")
    private Double balance;

    /** The company document number associated to the exam result*/
    @Column(name = "company_dni")
    private String companyDni;

    /** the source of the message*/
    @Column(name = "source")
    private String source;
}
