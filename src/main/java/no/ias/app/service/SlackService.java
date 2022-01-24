package no.ias.app.service;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Singleton
@Slf4j
public class SlackService {

    private final Slack slack;
    @Value("${slack.endpoint}")
    protected String slackEndpoint;

    @Inject
    public SlackService(Slack slack) {
        this.slack = slack;
    }

    public WebhookResponse sendSlackMessage(Payload payload) throws IOException {
        return slack.send(slackEndpoint, payload);
    }
}
