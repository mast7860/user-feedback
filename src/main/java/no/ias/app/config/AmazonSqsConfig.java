package no.ias.app.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
public class AmazonSqsConfig {

    @Value("${aws.region}")
    protected String awsRegion;
    @Value("${sqs.queue.url}")
    private String queueUrl;

    @Bean
    @Requires(notEnv = "local")
    public AmazonSQS sqs() {
        log.debug("Running inside AWS region={} and queue url={}", awsRegion, queueUrl);
        return AmazonSQSClientBuilder
                .standard()
                .withRegion(awsRegion)
                .build();
    }

    @Bean
    @Requires(env = "local")
    public AmazonSQS localSqs() {
        log.debug("Running local SQS Client");
        return AmazonSQSClientBuilder.standard().withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(queueUrl, awsRegion))
                .withClientConfiguration(new ClientConfiguration())
                .build();
    }
}
