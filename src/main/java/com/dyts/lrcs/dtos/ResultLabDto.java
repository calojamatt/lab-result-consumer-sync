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

    private String patientPrint;

    private String groupExamCode;

    private String groupName;

    /** The exam code of the result */
    private String examCode;

    /** The exam name of the result */
    private String examName;

    private String examReg;

    /** the analysis code of the exam result */
    private String analysisCode;

    /** The creation date of the exam */
    private Date creationDate;

    private String locationCode;

    private byte[] validSign;

    private byte[] resultImage;

    private byte[] secondSign;

    /** the unit measure of the exams */
    private String units;

    /** the minimum reference value of the exam */
    private String minimum;

    /** the maximum reference value of the exam */
    private String maximum;

    /** the intermediate reference value of the exam */
    private String intermediate;

    /** The full name of the patient */
    private String fullName;

    private String profession;

    private String tarProf;

    private String currentForm;

    private String totalForms;

    private String cCostDescription;

    private String secondSignName;

    private String secondSignProf;

    private String tarSecondSignProf;

    private String secondSignDir;

    private String validated;

    private String reviewed;

    /** the analysis of the patient */
    private String analysisF;

    private String unitsRes;

    private String resultF;

    private String resultQ;

    private String resultV;

    private String finalResult;

    private String mic;

    private String interP;

    /** The patient code of the lab result */
    private String patientCode;

    private String history;

    /** The patient document identity */
    private String dni;

    private Date enterDate;

    private String printDate;

    /** The first name of the patient*/
    private String firstName;

    /** The last name of the patient*/
    private String lastName;

    /** The creation hour of the exam */
    private String hour;

    /** The patient document type */
    private String documentType;

    /** The patient clte code */
    private String clientCode;

    /** The balance of the company */
    private String balance;

    /** The company document number associated to the exam result*/
    private String companyDni;

    private String patientName;

    private String doctorName;

    private String telephone;

    private String ageSex;

    private String locationName;

    private String clientName;

    private String reason;

    private String bebDet;

    private String nFac;

    /** the sync source */
    private String source;
}
