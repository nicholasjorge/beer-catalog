package dev.georgetech.beercatalog.catalog;

import dev.georgetech.beercatalog.catalog.model.BeerEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerEntryRepository extends CrudRepository<BeerEntry, Long> {

}
