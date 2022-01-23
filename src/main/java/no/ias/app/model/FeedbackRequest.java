package no.ias.app.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.validation.Validated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Introspected
@Validated
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "Customer Feedback", name = "Feedback")
public class FeedbackRequest {

    String feedback;

    int score;

    String firstName;

    String lastName;

    String emailId;
}
