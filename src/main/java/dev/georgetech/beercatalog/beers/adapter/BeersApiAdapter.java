package dev.georgetech.beercatalog.beers.adapter;

import dev.georgetech.beercatalog.beers.dto.Beer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeersApiAdapter {

    private static final String BEERS_ENDPOINT = "/beers";
    private static final int TIMEOUT_SECONDS = 5;

    private final WebClient webClient;

    public List<Beer> getBeers() {
        return callExternalApi(BEERS_ENDPOINT);
    }

    public Optional<Beer> getBeerById(String id) {
        List<Beer> beers = callExternalApi(BEERS_ENDPOINT + "/" + id);
        if (beers == null || beers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(beers.get(0));
    }

    public List<Beer> getBeersFiltered(Map<String, String> params) {
        MultiValueMap<String, String> queryParams = resolveQueryParams(params);
        List<Beer> beers = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BEERS_ENDPOINT)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
        log.info("beers:{}", beers);
        return beers;
    }

    private List<Beer> callExternalApi(String uri) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
    }

    private MultiValueMap<String, String> resolveQueryParams(Map<String, String> originalQueryParams) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        if (originalQueryParams.isEmpty()) {
            return queryParams;
        }
        Optional.ofNullable(originalQueryParams.get("food")).ifPresent(value -> queryParams.add("food", value.replace(" ", "_")));
        Optional.ofNullable(originalQueryParams.get("ibuMin")).ifPresent(value -> queryParams.add("ibu_gt", value));
        Optional.ofNullable(originalQueryParams.get("ibuMax")).ifPresent(value -> queryParams.add("ibu_lt", value));
        Optional.ofNullable(originalQueryParams.get("page")).ifPresent(value -> queryParams.add("page", value));
        Optional.ofNullable(originalQueryParams.get("size")).ifPresent(value -> queryParams.add("per_page", value));
        return queryParams;
    }

}