package no.ias.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.domain.FeedbackData;
import no.ias.app.model.FeedbackRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<FeedbackData> getAllFeedBack() {
        try {
            return dynamoDBMapper.scan(FeedbackData.class, new DynamoDBScanExpression()).stream()
                    .sorted(Comparator.comparing(FeedbackData::getTimeStampFeedback))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.warn("An error occurred while fetching feedback stats from dynamoDB. Error={}", e.getMessage());
            return Collections.emptyList();
        }
    }

}
