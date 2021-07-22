package dev.georgetech.beercatalog.beers.dto;

import lombok.Data;

@Data
public class Beer {
    private long id;
    private String name;
    private String tagline;
    private String firstBrewed;
    private String description;
    private String imageURL;
    private double abv;
    private double ibu;
    private double targetFg;
    private double targetOg;
    private double ebc;
    private double srm;
    private double ph;
    private double attenuationLevel;
    private BoilVolume volume;
    private BoilVolume boilVolume;
    private Method method;
    private Ingredients ingredients;
    private String[] foodPairing;
    private String brewersTips;
    private String contributedBy;

}