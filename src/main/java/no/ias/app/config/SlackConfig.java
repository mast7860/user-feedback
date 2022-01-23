package no.ias.app.config;

import com.slack.api.Slack;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

@Factory
public class SlackConfig {

    @Bean
    public Slack slack() {
        return Slack.getInstance();
    }
}
