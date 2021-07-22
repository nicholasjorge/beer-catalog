package dev.georgetech.beercatalog.beers.dto;

import lombok.Data;

@Data
public class Ingredients {
    private Malt[] malt;
    private Hop[] hops;
    private String yeast;

}