/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * Converter.java
 */
package com.dyts.lrcs.converters.api;

import java.util.List;

/**
 * Interface to convert class T to Class K
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 22/06/21 10:24 p. m.
 * @since 1.0.0
 */
public interface Converter<T, K> {

    /**
     * Transform Class k to Class t
     * @param k the class to convert
     * @return t the new object
     * */
    T convert(K k);

    /**
     * Transform Class k to Class t
     * @param k a list of k to convert
     * @return t a list of new objects
     * */
    List<T> convert(List<K> k);
}
