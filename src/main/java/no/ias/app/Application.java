package no.ias.app;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "user-feedback",
                version = "0.1",
                description = "The service collect the feedback from customers and process them"
        )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.build(args)
                .banner(false)
                .start();
    }
}
