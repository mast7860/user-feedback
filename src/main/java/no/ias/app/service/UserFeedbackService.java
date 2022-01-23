package no.ias.app.service;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.model.FeedbackRequest;

import java.util.UUID;

@Slf4j
@Singleton
public class UserFeedbackService {


    public void saveCustomerFeedback(FeedbackRequest feedbackRequest,
                                     UUID traceId){

    log.info("request processed for traceId={}", traceId);
    }
}
