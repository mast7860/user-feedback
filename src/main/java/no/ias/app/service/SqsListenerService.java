package no.ias.app.service;

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

    @Queue(value = "${sqs.queue.name}", concurrency = "${sqs.queue.threads}")
    public void receiveMessage(@MessageBody String message) {
        log.debug("message received {}", message);
        try {
            log.debug("message processed {}", message);
        } catch (Exception exception) {
            log.error("Error processing SqsMessage with error={}", exception.getMessage());
        }
    }
}
