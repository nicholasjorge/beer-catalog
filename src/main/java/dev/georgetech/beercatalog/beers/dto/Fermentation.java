package dev.georgetech.beercatalog.beers.dto;

import lombok.ToString;

@ToString
public class Fermentation {
    private BoilVolume temp;

    public BoilVolume getTemp() {
        return temp;
    }

    public void setTemp(BoilVolume value) {
        this.temp = value;
    }
}