package dev.georgetech.beercatalog.catalog.api;

import dev.georgetech.beercatalog.catalog.BeerEntryService;
import dev.georgetech.beercatalog.catalog.dto.BeerCatalog;
import dev.georgetech.beercatalog.catalog.dto.BeerEntryDto;
import dev.georgetech.beercatalog.catalog.model.BeerEntry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Beer Catalog API", description = "REST API that provides data about beer entries for a session")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BeerCatalogController {

    private final BeerEntryService beerEntryService;

    @Operation(summary = "Get beer catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found beer catalog",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeerCatalog.class))}),
            @ApiResponse(responseCode = "404", description = "Beer catalog not found",
                    content = {@Content})})
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerCatalog> getBeerCatalog() {
        var beerEntries = beerEntryService.getBeerEntries();
        Set<BeerEntryDto> entries = beerEntries.stream()
                .map(BeerEntryMapper::toDto)
                .collect(Collectors.toSet());
        if (entries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(BeerCatalog.of(entries));
    }

    @Operation(summary = "Get beer entry by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found beer entry",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeerEntryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Beer entry not found",
                    content = {@Content})})
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerEntryDto> getBeerEntryById(@PathVariable Long id) {
        var beerEntry = beerEntryService.getBeerEntryById(id);
        if (BeerEntry.EMPTY == beerEntry) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(BeerEntryMapper.toDto(beerEntry));
    }

    @Operation(summary = "Get beer entry by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found beer entry",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeerEntryDto.class))}),
            @ApiResponse(responseCode = "404", description = "Beer entry not found",
                    content = {@Content})})
    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<BeerEntryDto> getBeerEntryByName(@RequestParam String name) {
        var beerEntry = beerEntryService.getBeerEntryByName(name);
        if (BeerEntry.EMPTY == beerEntry) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(BeerEntryMapper.toDto(beerEntry));
    }

    @Operation(summary = "Create beer entry to catalog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beer entry created",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "Beer Entry Bad Request",
                    content = {@Content})})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBeerEntry(@RequestBody @Valid BeerEntryDto beerEntryDto) {
        Long entryId = beerEntryService.saveBeerEntry(BeerEntryMapper.toModel(beerEntryDto));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entryId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
