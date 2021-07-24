package dev.georgetech.beercatalog.catalog.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BEER_CATALOG",
        indexes = @Index(name = "beer_name_index", columnList = "beer_name")
)
public class BeerEntry {

    public static final BeerEntry EMPTY = new BeerEntry();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private BeerInfo beerInfo;

    private LocalDate date;
    private String location;
    private Integer rating;
    private String comments;

}
