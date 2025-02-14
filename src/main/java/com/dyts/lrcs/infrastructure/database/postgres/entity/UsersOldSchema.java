/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronization.java
 */
package com.dyts.lrcs.infrastructure.database.postgres.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Model Class to manage the user synchronization for redis
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 3:55 p. m.
 * @since 1.0.0
 */
@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class UsersOldSchema implements Serializable {

    /** the username */
    @Id
    @Column(name = "username")
    private String username;

    /** the user password */
    @Column(name = "password")
    private String password;

    /** the document identification type*/
    @Column(name = "document_type")
    private String documentType;

    /** the patient first name */
    @Column(name = "name")
    private String name;

    /** the patient second name */
    @Column(name = "lastname")
    private String lastName;

    /** the patient email*/
    @Column(name = "email")
    private String email;

    /** the status of the user */
    @Column(name = "state")
    private String state;

    /** the role of the user */
    @Column(name = "rol")
    private String rol;

    /** the source of the message*/
    @Column(name = "create_time")
    private Timestamp createTime;
}
