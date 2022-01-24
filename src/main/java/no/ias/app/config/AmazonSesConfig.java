package no.ias.app.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
public class AmazonSesConfig {

    @Value("${aws.region}")
    protected String awsRegion;
    @Value("${aws.local-endpoint}")
    protected String serviceEndpoint;

    @Bean
    @Requires(notEnv = "local")
    public AmazonSimpleEmailService simpleEmailService() {
        log.debug("Running inside AWS region={}", awsRegion);
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(awsRegion).build();
    }

    @Bean
    @Requires(env = "local")
    public AmazonSimpleEmailService localSimpleEmailService() {
        log.debug("Running local SES Client");
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(serviceEndpoint, awsRegion))
                .withClientConfiguration(new ClientConfiguration())
                .build();
    }

}
