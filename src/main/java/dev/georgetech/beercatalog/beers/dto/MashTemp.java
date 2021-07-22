package dev.georgetech.beercatalog.beers.dto;

import lombok.Data;

@Data
public class MashTemp {
    private BoilVolume temp;
    private long duration;

}