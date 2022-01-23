package no.ias.app.service;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.domain.FeedbackData;
import no.ias.app.model.FeedbackRequest;
import no.ias.app.repository.UserFeedbackRepository;
import no.ias.app.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Singleton
public class UserFeedbackService {

    private final UserFeedbackRepository userFeedbackRepository;

    private final SqsSenderService sqsSenderService;

    private final EmailService emailService;


    public UserFeedbackService(UserFeedbackRepository userFeedbackRepository,
                               SqsSenderService sqsSenderService,
                               EmailService emailService) {
        this.userFeedbackRepository = userFeedbackRepository;
        this.sqsSenderService = sqsSenderService;
        this.emailService = emailService;
    }

    public String saveCustomerFeedback(FeedbackRequest feedbackRequest) {

        userFeedbackRepository.saveFeedback(feedbackRequest);

        var formattedMessage = "feedback= %s & rating= %s".formatted(feedbackRequest.getFeedback(), feedbackRequest.getScore());

        sqsSenderService.send(formattedMessage);

        return "success";
    }

    public List<FeedbackData> getCustomerFeedback() {

        return userFeedbackRepository.getAllFeedBack();


    }

    public void sendFeedbackStatsViaEmail() {

        var feedbacks = userFeedbackRepository.getAllFeedBack();

        Map<Integer, Long> countMap = feedbacks.stream().collect(groupingBy(FeedbackData::getScore, counting()));

        long totalFeedbacks = 0;
        for (Map.Entry<Integer, Long> entry : countMap.entrySet()) {
            totalFeedbacks += entry.getValue();
        }

        log.info("totalFeedbacks = " + totalFeedbacks);

        Map<Integer, Double> percentageMap = new HashMap<>();
        for (Map.Entry<Integer, Long> entry : countMap.entrySet()) {
            percentageMap.put(entry.getKey(), entry.getValue().doubleValue()*100/totalFeedbacks);
        }

        emailService.sendEmail(Utils.updatedHtmlTemplate(percentageMap));

    }
}
