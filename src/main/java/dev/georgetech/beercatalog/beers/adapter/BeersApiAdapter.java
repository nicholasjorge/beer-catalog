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
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeersApiAdapter {

    //TODO extract to config props instead of static init block
    private static final Map<String, String> TYPE_TO_TEMP_INTERVAL = new HashMap<>();
    private static final int TIMEOUT_SECONDS = 5;
    private final WebClient webClient;

    //TODO group remote api query params to these categories/fermentation types
    static {
        TYPE_TO_TEMP_INTERVAL.put("top", "");
        TYPE_TO_TEMP_INTERVAL.put("medium", "");
        TYPE_TO_TEMP_INTERVAL.put("bottom", "");
    }

    //TODO remove duplication
    public List<Beer> getBeers() {
        return webClient.get()
                .uri("/beers")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    public Optional<Beer> getBeerById(Long id) {
        List<Beer> beers = webClient.get()
                .uri("/beers/" + id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
        if (beers == null || beers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(beers.get(0));
    }

    public List<Beer> getBeersFiltered(String fermentationType,
                                       String food,
                                       Map<String, String> ibuParams) {
        //TODO validate/process params
        //TODO forward params to remote API
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