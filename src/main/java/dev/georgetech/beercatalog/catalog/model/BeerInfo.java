package dev.georgetech.beercatalog.catalog.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@ToString
public class BeerInfo {

    private String beerId;
    @Column(name = "beer_name", unique = true)
    private String beerName;
}
