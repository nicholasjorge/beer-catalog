package dev.georgetech.beercatalog.beers.dto;

import lombok.Data;

@Data
public class Hop {
    private String name;
    private BoilVolume amount;
    private String add;
    private String attribute;

}