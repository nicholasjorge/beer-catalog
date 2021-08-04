package dev.georgetech.beercatalog.catalog;

import dev.georgetech.beercatalog.catalog.model.BeerEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerEntryRepository extends CrudRepository<BeerEntry, Long> {

    Optional<BeerEntry> findByBeerInfoBeerNameContainingIgnoreCase(String beerName);

}
