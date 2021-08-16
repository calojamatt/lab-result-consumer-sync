/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSynchronizationRedis.java
 */
package com.dyts.lrcs.infrasctructure.database.redis.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
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
public class UserSynchronizationRedis implements Serializable {

    /** the document identification id*/
    @Id
    private String dni;

    /** the document identification type*/
    private String dniType;

    /** the patient first name */
    private String firstName;

    /** the patient second name */
    private String lastName;

    /** the patient email*/
    private String email;

    /** the status of the sync process for the record */
    private String status;
}
