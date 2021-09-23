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

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "result_lab", schema = "results")
public class ResultLab implements Serializable {

    /** The table id */
    @Id
    private String id;

    @Column(name = "pacimpri")
    private String patientPrint;

    @Column(name = "gruexa_cod")
    private String groupExamCode;

    @Column(name = "nomgru")
    private String groupName;

    /** The exam code of the result */
    @Column(name = "examen_cod")
    private String examCode;

    /** The exam name of the result */
    @Column(name = "nomexa")
    private String examName;

    @Column(name = "reg_exa")
    private Integer examReg;

    /** the analysis code of the exam result */
    @Column(name = "analito_cod")
    private String analysisCode;

    /** The creation date of the exam */
    @Column(name = "fecha")
    private Date creationDate;

    @Column(name = "sede_codigo")
    private String locationCode;

    @Column(name = "firma_valido")
    private byte[] validSign;

    @Column(name = "imagen_resultado")
    private byte[] resultImage;

    @Column(name = "sgda_firma")
    private byte[] secondSign;

    /** the unit measure of the exams */
    @Column(name = "unidades")
    private String units;

    /** the minimum reference value of the exam */
    @Column(name = "minimo")
    private String minimum;

    /** the maximum reference value of the exam */
    @Column(name = "maximo")
    private String maximum;

    /** the intermediate reference value of the exam */
    @Column(name = "intermedio")
    private String intermediate;

    /** The full name of the patient */
    @Column(name = "nombre_completo")
    private String fullName;

    @Column(name = "profesion")
    private String profession;

    @Column(name = "tar_prof")
    private String tarProf;

    @Column(name = "forma_actual")
    private Integer currentForm;

    @Column(name = "total_formas")
    private Integer totalForms;

    @Column(name = "ccosto_desc")
    private String cCostDescription;

    @Column(name = "nombre_segunda_firma")
    private String secondSignName;

    @Column(name = "prof_segda_firma")
    private String secondSignProf;

    @Column(name = "tar_prof_sgda_firma")
    private String tarSecondSignProf;

    @Column(name = "dir_sgda_firma")
    private String secondSignDir;

    @Column(name = "validado")
    private String validated;

    @Column(name = "revisado")
    private String reviewed;

    /** the analysis of the patient */
    @Column(name = "analitof")
    private String analysisF;
    @Column(name = "unidad_res")
    private String unitsRes;

    @Column(name = "resultadof")
    private String resultF;

    @Column(name = "resultadoq")
    private String resultQ;

    @Column(name = "resultadov")
    private String resultV;

    @Column(name = "resultadofinal")
    private String finalResult;

    @Column(name = "mic")
    private String mic;

    @Column(name = "interp")
    private String interP;

    /** The patient code of the lab result */
    @Column(name = "paciente_cod")
    private String patientCode;

    @Column(name = "historia")
    private String history;

    /** The patient document identity */
    @Column(name = "nit")
    private String dni;

    @Column(name = "fec_ing")
    private String enterDate;

    @Column(name = "fec_imp")
    private Date printDate;

    /** The first name of the patient*/
    @Column(name = "nom1")
    private String firstName;

    /** The last name of the patient*/
    @Column(name = "ape1")
    private String lastName;

    /** The creation hour of the exam */
    @Column(name = "hora")
    private String hour;

    /** The patient document type */
    @Column(name = "tipodcto_cod")
    private String documentType;

    /** The patient clte code */
    @Column(name = "clte_codigo")
    private String clientCode;

    /** The balance of the company */
    @Column(name = "saldo")
    private Double balance;

    /** The company document number associated to the exam result*/
    @Column(name = "empresa")
    private String companyDni;

    @Column(name = "nombre_pac")
    private String patientName;

    @Column(name = "nombre_med")
    private String doctorName;

    @Column(name = "telefono")
    private String telephone;

    @Column(name = "edad_sexo")
    private String ageSex;

    @Column(name = "nombre_sede")
    private String locationName;

    @Column(name = "nombre_clte")
    private String clientName;

    @Column(name = "razon")
    private String reason;

    @Column(name = "cama_det")
    private String bebDet;

    @Column(name = "n_fac")
    private String nFac;

    /** the sync source of the message */
    @Column
    private String source;
}
