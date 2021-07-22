package dev.georgetech.beercatalog.catalog.api;

import dev.georgetech.beercatalog.catalog.dto.BeerEntryDto;
import dev.georgetech.beercatalog.catalog.model.BeerEntry;
import dev.georgetech.beercatalog.catalog.model.BeerInfo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BeerEntryMapper {

    public static BeerEntryDto toDto(BeerEntry model) {
        return BeerEntryDto.builder()
                .id(String.valueOf(model.getId()))
                .beerId(model.getBeerInfo().getBeerId())
                .beerName(model.getBeerInfo().getBeerName())
                .location(model.getLocation())
                .date(model.getDate())
                .rating(model.getRating())
                .comments(model.getComments())
                .build();
    }

    public static BeerEntry toModel(BeerEntryDto dto) {
        return BeerEntry.builder()
                .date(dto.getDate())
                .location(dto.getLocation())
                .rating(dto.getRating())
                .comments(dto.getComments())
                .beerInfo(BeerInfo.builder()
                        .beerId(dto.getBeerId())
                        .beerName(dto.getBeerName())
                        .build())
                .build();
    }
}
