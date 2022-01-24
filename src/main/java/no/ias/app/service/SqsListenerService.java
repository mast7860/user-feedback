package no.ias.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.webhook.Payload;
import io.micronaut.context.annotation.Requires;
import io.micronaut.jms.annotations.JMSListener;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;
import lombok.extern.slf4j.Slf4j;

import static io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSListener(CONNECTION_FACTORY_BEAN_NAME)
@Slf4j
@Requires(notEnv = "test")
public class SqsListenerService {

    private final SlackService slackService;

    public SqsListenerService(SlackService slackService) {
        this.slackService = slackService;
    }

    @Queue(value = "${sqs.queue.name}", concurrency = "${sqs.queue.threads}")
    public void receiveMessage(@MessageBody String message) {
        log.info("message received {}", message);
        try {
            var payload = Payload.builder().text(message).build();
            slackService.sendSlackMessage(payload).getBody();
            log.info("message processed {}", message);
        } catch (Exception exception) {
            log.error("Error processing SqsMessage with error={}", exception.getMessage());
        }
    }
}
