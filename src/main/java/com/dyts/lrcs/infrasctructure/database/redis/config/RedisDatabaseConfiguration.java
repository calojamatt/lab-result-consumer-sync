/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2021
 * All right reserved.
 *
 * lab-results
 * RedisDatabaseConfiguration.java
 */
package com.dyts.lrcs.infrasctructure.database.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Class to manage redis database configuration
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 10:39 a. m.
 * @since 1.0.0
 */
@Configuration
public class RedisDatabaseConfiguration {

    /** the redis bootstrap server */
    @Value("${redis.bootstrap-server}")
    private String redisServer;

    /** the redis bootstrap server port */
    @Value("${redis.bootstrap-server.port}")
    private Integer redisServerPort;

    /** set the connection factory for redis */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        var redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisServer, redisServerPort);

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    /** redis template for database operations */
    @Bean("redisTemplate")
    <T> RedisTemplate<String, T> redisTemplate() {

        final RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
