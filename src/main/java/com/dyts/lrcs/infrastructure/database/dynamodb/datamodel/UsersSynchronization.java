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
package com.dyts.lrcs.infrastructure.database.dynamodb.datamodel;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model Class to manage the user synchronization for redis
 *
 * @author <a href:"carlos.maturana@dytssol.com">Carlos Maturana</a>
 * @version 1.0.1
 * @created 26/06/21 3:55 p. m.
 * @since 1.0.0
 */
@Builder(setterPrefix = "with")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "labresult-users")
public class UsersSynchronization {

    /** the partition key on dynamodb */
    @DynamoDBHashKey
    private String user;

    /** the document identification id*/
    @DynamoDBAttribute
    private String username;

    /** the document identification id*/
    @DynamoDBAttribute
    private String password;

    /** the document identification id*/
    @DynamoDBAttribute
    private String dni;

    /** the document identification type*/
    @DynamoDBAttribute
    private String documentType;

    /** the patient first name */
    @DynamoDBAttribute
    private String firstName;

    /** the patient second name */
    @DynamoDBAttribute
    private String lastName;

    /** the patient email*/
    @DynamoDBAttribute
    private String email;

    /** the patient email*/
    @DynamoDBAttribute
    private String state;

    /** the patient email*/
    @DynamoDBAttribute
    private String rol;

    /** the document identification id*/
    @DynamoDBAttribute
    private String source;
}
