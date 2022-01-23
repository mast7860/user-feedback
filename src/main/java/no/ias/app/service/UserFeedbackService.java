package no.ias.app.service;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.model.FeedbackRequest;
import no.ias.app.repository.UserFeedbackRepository;

@Slf4j
@Singleton
public class UserFeedbackService {

    private final UserFeedbackRepository userFeedbackRepository;

    private final SqsSenderService sqsSenderService;


    public UserFeedbackService(UserFeedbackRepository userFeedbackRepository, SqsSenderService sqsSenderService) {
        this.userFeedbackRepository = userFeedbackRepository;
        this.sqsSenderService = sqsSenderService;
    }

    public String saveCustomerFeedback(FeedbackRequest feedbackRequest) {

        userFeedbackRepository.saveFeedback(feedbackRequest);

        var formattedMessage = "feedback= %s & rating= %s".formatted(feedbackRequest.getFeedback(), feedbackRequest.getScore());

        sqsSenderService.send(formattedMessage);

        return "success";

    }
}
