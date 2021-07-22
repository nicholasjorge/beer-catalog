package dev.georgetech.beercatalog.beers.api;

import dev.georgetech.beercatalog.beers.adapter.BeersApiAdapter;
import dev.georgetech.beercatalog.beers.dto.Beer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BeersController {

    private static final int TIMEOUT_SECONDS = 5;
    private final WebClient webClient;
    private final BeersApiAdapter beersApiAdapter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Beer> getBeers() {
        List<Beer> beers = webClient.get()
                .uri("/beers")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new IllegalStateException("server_error")))
                .bodyToFlux(Beer.class)
                .collectList()
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
        log.info("Beers: {}", beers);
        return beers;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Beer[] getBeer(@PathVariable String id) {
        Beer[] beer = webClient.get()
                .uri("/beers/" + id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.just(new IllegalArgumentException("client_error")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.just(new IllegalStateException("server_error")))
                .bodyToMono(Beer[].class)
                .block(Duration.ofSeconds(TIMEOUT_SECONDS));
        log.info("Beer: {}", beer);
        return beer;
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Beer> getBeersFiltered(@RequestParam(required = false) String fermentationType,
                                       @RequestParam(required = false) String food,
                                       @RequestParam(required = false) Map<String, String> ibuParams) {
        List<Beer> beers = beersApiAdapter.getBeers(fermentationType, food, ibuParams);
        log.info("Beers: {}", beers);
        return beers;
    }


}
