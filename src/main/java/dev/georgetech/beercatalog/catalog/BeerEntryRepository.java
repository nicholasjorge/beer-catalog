package dev.georgetech.beercatalog.catalog;

import dev.georgetech.beercatalog.catalog.model.BeerEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeerEntryRepository extends CrudRepository<BeerEntry, Long> {

    @Query("select be from BeerEntry be where be.beerInfo.beerName like '%name%'")
    Optional<BeerEntry> findBeerEntryByName(@Param(value = "name") String name);

}
