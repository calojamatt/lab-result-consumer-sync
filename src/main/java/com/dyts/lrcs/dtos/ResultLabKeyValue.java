/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-result-produce-sync
 * ResultLabKeyValue.java
 */
package com.dyts.lrcs.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Class to // TODO
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 17/08/21 11:39 p. m.
 * @since 1.0.0
 */
@Builder
@Data
public class ResultLabKeyValue {

    private String patientCode;
    private List<ResultLabDto> examList;
}
