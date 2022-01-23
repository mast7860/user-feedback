package no.ias.app.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
public class AmazonDynamoDBConfiguration {

    @Value("${aws.region}")
    protected String awsRegion;
    @Value("${aws.local-endpoint}")
    protected String serviceEndpoint;

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB dynamoDBClient) {
        DynamoDBMapperConfig dynamoDBMapperConfig = DynamoDBMapperConfig.builder().build();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDBClient, dynamoDBMapperConfig);
        log.info("Created DynamoDBMapper {} with client {}", dynamoDBMapper, dynamoDBClient);
        return dynamoDBMapper;
    }

    @Bean
    @Requires(notEnv = "local")
    public AmazonDynamoDB dynamoDB() {
        log.info("Creating default DynamoDB client in region={}", awsRegion);
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
    }

    @Bean
    @Requires(env = "local")
    public AmazonDynamoDB localDynamoDB() {
        log.info("Creating local DynamoDB client");
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(serviceEndpoint, awsRegion))
                .withClientConfiguration(new ClientConfiguration())
                .build();
    }
}
