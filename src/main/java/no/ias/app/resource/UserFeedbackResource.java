package no.ias.app.resource;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import no.ias.app.domain.FeedbackData;
import no.ias.app.model.FeedbackRequest;
import no.ias.app.service.UserFeedbackService;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Controller(value = "/v1")
@Slf4j
@Introspected(classes = {FeedbackRequest.class, UUID.class})
@Validated
public class UserFeedbackResource {

    private final UserFeedbackService userFeedbackService;

    @Inject
    public UserFeedbackResource(UserFeedbackService userFeedbackService) {
        this.userFeedbackService = userFeedbackService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "save customer feedback",
            description = "Save and process feedback shared by customer")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Tag(name = "Feedback")
    public Mono<HttpResponse<String>> saveCustomerFeedback(@NotEmpty @Valid @Body FeedbackRequest feedbackRequest,
                                                           @Header UUID traceId) {
        log.info("request received for traceId={}", traceId);

        return Mono.just(HttpResponse.ok(userFeedbackService.saveCustomerFeedback(feedbackRequest)));
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "get customer feedback",
            description = "process request and email feedback stats")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @Tag(name = "Feedback")
    public Mono<HttpResponse<List<FeedbackData>>> getCustomerFeedbackStats(@Header UUID traceId) {
        log.info("request received for traceId={}", traceId);

        return Mono.just(HttpResponse.ok(userFeedbackService.getCustomerFeedback()));
    }
}
