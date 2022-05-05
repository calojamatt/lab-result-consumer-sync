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

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class UserSynchronizationDto implements Serializable {

    /** the document identification id*/
    private final String dni;

    /** the document identification type*/
    private final String dniType;

    /** the patient first name */
    private final String firstName;

    /** the patient second name */
    private final String lastName;

    /** the patient email*/
    private final String email;

    /** the source of the message*/
    private final String source;
}
