/*
 * Development and Technologies Solutions S.A.S - D&TS
 * www.dytssol.com
 *
 * Copyright © 2015 - 2022
 * All right reserved.
 *
 * lab-result-consumer-sync
 * DynamoDbConfiguration
 */
package com.dyts.lrcs.infrastructure.database.dynamodb.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Enable the use of dynamodb
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.0
 * @created 5/05/22 23
 * @since 1.0.0
 */
@Configuration
public class DynamoDbConfiguration {

    @Value("${cloud.aws.dynamodb.endpoint}")
    private String dynamodbEndpoint;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${cloud.aws.credentials.access-key}")
    private String dynamodbAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String dynamodbSecretKey;


    @Bean
    public DynamoDBMapper dynamoDBMapper() {

        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {

        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(dynamodbAccessKey, dynamodbSecretKey)))
                .build();
    }
}
