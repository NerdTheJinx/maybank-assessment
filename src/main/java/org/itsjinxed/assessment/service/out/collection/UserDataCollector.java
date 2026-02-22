package org.itsjinxed.assessment.service.out.collection;

import jakarta.annotation.PostConstruct;
import org.itsjinxed.assessment.service.out.model.UserFragment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class UserDataCollector implements DataCollector<UserFragment> {

    @Value("${services.user.url}")
    private String userApiEndpoint;

    private WebClient webClient;

    @PostConstruct
    void setupWebClient() {
        // userApiEndpoint does not initiate in the default constructor
        this.webClient = WebClient.create(userApiEndpoint);
    }

    @Override
    public Flux<UserFragment> collect() {
        return this.webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UserFragment.class);
    }

}
