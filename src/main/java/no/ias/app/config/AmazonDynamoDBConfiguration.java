package no.ias.app.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
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
    @Requires(notEnv = "local")
    public DynamoDB dynamoDB() {
        log.info("Creating default DynamoDB client in region={}", awsRegion);
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
        return new DynamoDB(client);
    }

    @Bean
    @Requires(env = "local")
    public DynamoDB localDynamoDB() {
        log.info("Creating local DynamoDB client");
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(serviceEndpoint, awsRegion))
                .withClientConfiguration(new ClientConfiguration())
                .build();
        return new DynamoDB(client);
    }
}
