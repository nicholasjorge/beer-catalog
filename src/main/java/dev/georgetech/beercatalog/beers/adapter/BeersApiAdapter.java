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
        TYPE_TO_TEMP_INTERVAL.put("top", "15-25");
        TYPE_TO_TEMP_INTERVAL.put("medium", "10-15");
        TYPE_TO_TEMP_INTERVAL.put("bottom", "7-10");
    }

    public List<Beer> getBeers() {
        return callExternalApi("/beers");
    }

    public Optional<Beer> getBeerById(Long id) {
        List<Beer> beers = callExternalApi("/beers/" + id);
        if (beers == null || beers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(beers.get(0));
    }

    public List<Beer> getBeersFiltered(Map<String, String> params) {
        MultiValueMap<String, String> queryParams = resolveQueryParams(params);
        List<Beer> beers = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/beers")
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
        log.info("beers:{}", beers);
        return filterFermentationTemperature(params, beers);
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
        Optional.ofNullable(originalQueryParams.get("food")).ifPresent(value -> queryParams.add("food", value));
        Optional.ofNullable(originalQueryParams.get("ibuMin")).ifPresent(value -> queryParams.add("ibu_gt", value));
        Optional.ofNullable(originalQueryParams.get("ibuMax")).ifPresent(value -> queryParams.add("ibu_lt", value));
        return queryParams;
    }

    private List<Beer> filterFermentationTemperature(Map<String, String> params, List<Beer> beers) {
        Optional<String> fermentationType = Optional.ofNullable(params.get("fermentationType"));
        //TODO filter beers based on temperature for fermentation filtering
        return beers;
    }

}