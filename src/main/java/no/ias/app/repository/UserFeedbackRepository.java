package no.ias.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.domain.FeedbackData;
import no.ias.app.model.FeedbackRequest;

import java.time.LocalDateTime;

@Slf4j
@Singleton
public class UserFeedbackRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public UserFeedbackRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }


    public void saveFeedback(FeedbackRequest feedbackRequest) {

        FeedbackData feedbackData = FeedbackData.builder()
                .emailId(feedbackRequest.getEmailId())
                .timeStampFeedback(LocalDateTime.now())
                .firstName(feedbackRequest.getFirstName())
                .lastName(feedbackRequest.getLastName())
                .score(feedbackRequest.getScore())
                .feedback(feedbackRequest.getFeedback())
                .build();

        dynamoDBMapper.save(feedbackData);

        log.info("Saved feedback in database {}", feedbackRequest);

    }

}
