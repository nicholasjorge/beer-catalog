package dev.georgetech.beercatalog.beers.dto;

import lombok.ToString;

@ToString
public class Ingredients {
    private Malt[] malt;
    private Hop[] hops;
    private String yeast;

    public Malt[] getMalt() {
        return malt;
    }

    public void setMalt(Malt[] value) {
        this.malt = value;
    }

    public Hop[] getHops() {
        return hops;
    }

    public void setHops(Hop[] value) {
        this.hops = value;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String value) {
        this.yeast = value;
    }
}