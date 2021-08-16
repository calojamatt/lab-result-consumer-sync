/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * JsonConverter.java
 */
package com.dyts.lrcs.converters.impl;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to converts object to json string and viewers
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 21/06/21 1:25 p. m.
 * @since 1.0.0
 */
@Slf4j
public class JsonConverterImpl {

    /** private constructor to avoid instantiation */
    private JsonConverterImpl() {}

    /**
     * Converts a String into an Object
     * @param jsonList  a list of json string to convert
     * @param clazz the class to convert
     * @return the new object
     * */
    public static <T> List<T> fromJson(List<String> jsonList, Class<T> clazz) {

        return jsonList.stream().map(data -> fromJson(data, clazz)).collect(Collectors.toList());
    }

    /**
     * Converts a String into an Object
     * @param json the json string to convert
     * @param clazz the class to convert
     * @return the new object
     * */
    public static <T> T fromJson(String json, Class<T> clazz) {

        return new Gson().fromJson(json, clazz);
    }

    /**
     * Converts an Object into String
     * @param object the object to convert
     * @return a String in json format
     * */
    public static String toJson(Object object) {

        return new Gson().toJson(object);
    }



}
