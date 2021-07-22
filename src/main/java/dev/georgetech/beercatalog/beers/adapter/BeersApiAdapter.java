package dev.georgetech.beercatalog.beers.adapter;

import dev.georgetech.beercatalog.beers.dto.Beer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeersApiAdapter {

    //TODO extract to config props file
    private static final Map<String, String> TYPE_TO_TEMP_INTERVAL = new HashMap<>();
    private static final int TIMEOUT_SECONDS = 5;
    private final WebClient webClient;

    static {
        TYPE_TO_TEMP_INTERVAL.put("top", "");
        TYPE_TO_TEMP_INTERVAL.put("medium", "");
        TYPE_TO_TEMP_INTERVAL.put("bottom", "");
    }

    public List<Beer> getBeers(String fermentationType,
                               String food,
                               Map<String, String> ibuParams) {
        List<Beer> beers = webClient.get()
                .uri("/beers")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
        log.info("beers:{}", beers);
        return beers;
    }


}
