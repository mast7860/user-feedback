package no.ias.app.domain;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.ias.app.util.LocalDateTimeConverter;

import java.time.LocalDateTime;

@DynamoDBTable(tableName = "feedback")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackData {

    @DynamoDBHashKey(attributeName = "emailId")
    private String emailId;

    @DynamoDBRangeKey(attributeName = "timeStampFeedback")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime timeStampFeedback;

    @DynamoDBAttribute(attributeName = "score")
    private int score;

    @DynamoDBAttribute(attributeName = "firstName")
    private String firstName;

    @DynamoDBAttribute(attributeName = "lastName")
    private String lastName;

    @DynamoDBAttribute(attributeName = "feedback")
    private String feedback;

}