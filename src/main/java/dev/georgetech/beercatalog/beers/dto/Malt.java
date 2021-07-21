package dev.georgetech.beercatalog.beers.dto;

import lombok.ToString;

@ToString
public class Malt {
    private String name;
    private BoilVolume amount;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public BoilVolume getAmount() {
        return amount;
    }

    public void setAmount(BoilVolume value) {
        this.amount = value;
    }
}