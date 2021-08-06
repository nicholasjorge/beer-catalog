package dev.georgetech.beercatalog.beers.api;

import dev.georgetech.beercatalog.beers.adapter.BeersApiAdapter;
import dev.georgetech.beercatalog.beers.dto.Beer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Beers Controller API", description = "Provides data about beer")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/beers")
@RestController
public class BeersController {

    private final BeersApiAdapter beersApiAdapter;

    @Operation(summary = "Get beers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beers found",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Beer.class)))}),
            @ApiResponse(responseCode = "404", description = "Beers not found",
                    content = {@Content})})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Beer>> getBeers() {
        List<Beer> beers = beersApiAdapter.getBeers();
        log.info("Beers: {}", beers);
        if (beers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(beers);
    }

    @Operation(summary = "Get beer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beer found",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Beer.class))}),
            @ApiResponse(responseCode = "404", description = "Beer not found",
                    content = {@Content})})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beer> getBeer(@PathVariable Long id) {
        Optional<Beer> optionalBeer = beersApiAdapter.getBeerById(id);
        log.info("Beer: {}", optionalBeer);
        return optionalBeer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get beers filtered")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beers found",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Beer.class)))}),
            @ApiResponse(responseCode = "404", description = "Beers not found",
                    content = {@Content})})
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Beer>> getBeersFiltered(@RequestParam(defaultValue = "") Map<String, String> params) {//required=false makes swagger not show the params input
        List<Beer> beers = beersApiAdapter.getBeersFiltered(params);
        log.info("Beers: {}", beers);
        if (beers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(beers);
    }

}