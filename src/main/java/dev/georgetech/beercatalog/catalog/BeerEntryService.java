package dev.georgetech.beercatalog.catalog;

import dev.georgetech.beercatalog.catalog.model.BeerEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerEntryService {

    private final BeerEntryRepository beerEntryRepository;

    public Collection<BeerEntry> getBeerEntries() {
        return (Collection<BeerEntry>) beerEntryRepository.findAll();
    }

    public Long saveBeerEntry(BeerEntry beerEntry) {
        BeerEntry saved = beerEntryRepository.save(beerEntry);
        return saved.getId();
    }

    public BeerEntry getBeerEntryByName(String name) {
        return beerEntryRepository.findBeerEntryByName(name)
                .orElse(BeerEntry.EMPTY);
    }

    public BeerEntry getBeerEntryById(Long id) {
        return beerEntryRepository.findById(id)
                .orElse(BeerEntry.EMPTY);
    }
}
