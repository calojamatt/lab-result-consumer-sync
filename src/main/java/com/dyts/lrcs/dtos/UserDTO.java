/*
 * Development and Technologies Solutions S.A.S
 * lab-results
 * UserDTO.java
 *
 * Derechos de Autor 2015-2019 D&TS ©
 * Todos los Derechos Reservados.
 */
package com.dyts.lrcs.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 08/12/2019 2:14 AM
 */
@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String documentType;
    private String name;
    private String lastName;
    private String email;
    private String state;
    private String rol;
}
