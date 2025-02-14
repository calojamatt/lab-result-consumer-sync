/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * UserSincronization.java
 */
package com.dyts.lrcs.dtos;

import lombok.*;

import java.io.Serializable;

/**
 * Class to manage user synchronization data
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 20/06/21 11:08 p. m.
 * @since 1.0.0
 */
@Builder(setterPrefix = "with")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSynchronizationDto implements Serializable {

    /** the document identification id*/
    private String dni;

    /** the document identification type*/
    private String dniType;

    /** the patient first name */
    private String firstName;

    /** the patient second name */
    private String lastName;

    /** the patient email*/
    private String email;

    /** the source of the message*/
    private String source;
}
