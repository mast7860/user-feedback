package no.ias.app.service;

import io.micronaut.context.annotation.Requires;
import io.micronaut.jms.annotations.JMSProducer;
import io.micronaut.jms.annotations.Queue;
import io.micronaut.messaging.annotation.MessageBody;

import static io.micronaut.jms.sqs.configuration.SqsConfiguration.CONNECTION_FACTORY_BEAN_NAME;

@JMSProducer(CONNECTION_FACTORY_BEAN_NAME)
@Requires(notEnv = "test")
public interface SqsSenderService {

    @Queue(value = "${sqs.queue.name}", concurrency = "${sqs.queue.threads}")
    void send(@MessageBody String body);
}
