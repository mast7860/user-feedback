package no.ias.app.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.config.EmailConfig;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Singleton
public class EmailService {

    private final AmazonSimpleEmailService emailClient;
    private final EmailConfig emailConfig;

    @Inject
    public EmailService(AmazonSimpleEmailService emailClient,
                        EmailConfig emailConfig) {
        this.emailClient = emailClient;
        this.emailConfig = emailConfig;
    }

    public void sendEmail(String message) {

        SendEmailRequest sesRequest = new SendEmailRequest()
                .withDestination(getEmailDestination())
                .withMessage(getMessageBodyWithHtml(message))
                .withSource(emailConfig.getFrom());

        try {
            emailClient.sendEmail(sesRequest);
            log.info("email sent");
        } catch (AmazonSimpleEmailServiceException ex) {
            log.error("exception while sending message with error={}", ex.getMessage());
        }

    }

    private Destination getEmailDestination() {

        Destination destination = new Destination();

        List<String> list = Arrays.asList(emailConfig.getTo().split(","));
        destination.setToAddresses(list);

        return destination;
    }

    private Message getMessageBodyWithHtml(String input) {
        Body body = new Body()
                .withHtml(new Content().withCharset(StandardCharsets.UTF_8.displayName()).withData(input));

        return new Message()
                .withBody(body)
                .withSubject(
                        new Content().withCharset(StandardCharsets.UTF_8.displayName())
                                .withData(emailConfig.getSubject()));
    }

}
