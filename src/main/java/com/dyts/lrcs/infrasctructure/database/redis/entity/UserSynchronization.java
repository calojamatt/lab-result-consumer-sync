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
package com.dyts.lrcs.infrasctructure.database.redis.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Model Class to manage the user synchronization for redis
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 3:55 p. m.
 * @since 1.0.0
 */
@Builder(setterPrefix = "with")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("USERS")
public class UserSynchronization implements Serializable {

    /** the document identification id*/
    private String dni;

    /** the username */
    private String username;

    private String password;

    /** the document identification type*/
    private String documentType;

    /** the patient first name */
    private String name;

    /** the patient second name */
    private String lastName;

    /** the patient email*/
    private String email;

    /** the status of the user */
    private String state;

    /** the role of the user */
    private String rol;

    /** the source of the message*/
    private String source;
}
