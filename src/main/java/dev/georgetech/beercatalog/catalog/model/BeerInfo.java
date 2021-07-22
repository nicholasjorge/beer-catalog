package dev.georgetech.beercatalog.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class BeerInfo {

    private String beerId;
    private String beerName;
}
