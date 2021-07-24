package dev.georgetech.beercatalog.catalog.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.Set;

@Value(staticConstructor = "of")
public class BeerCatalog {

    public Set<BeerEntryDto> beerEntries;
    public LocalDate localDate = LocalDate.now();
}
