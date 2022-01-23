package no.ias.app.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties("ses.email")
public class EmailConfig {

    private String subject;
    private String from;
    private String to;
}
