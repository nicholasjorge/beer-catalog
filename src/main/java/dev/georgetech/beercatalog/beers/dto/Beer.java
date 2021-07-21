package dev.georgetech.beercatalog.beers.dto;

import lombok.ToString;

@ToString
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

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String value) {
        this.tagline = value;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String value) {
        this.firstBrewed = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String value) {
        this.imageURL = value;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double value) {
        this.abv = value;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double value) {
        this.ibu = value;
    }

    public double getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(double value) {
        this.targetFg = value;
    }

    public double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(double value) {
        this.targetOg = value;
    }

    public double getEbc() {
        return ebc;
    }

    public void setEbc(double value) {
        this.ebc = value;
    }

    public double getSrm() {
        return srm;
    }

    public void setSrm(double value) {
        this.srm = value;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double value) {
        this.ph = value;
    }

    public double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(double value) {
        this.attenuationLevel = value;
    }

    public BoilVolume getVolume() {
        return volume;
    }

    public void setVolume(BoilVolume value) {
        this.volume = value;
    }

    public BoilVolume getBoilVolume() {
        return boilVolume;
    }

    public void setBoilVolume(BoilVolume value) {
        this.boilVolume = value;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method value) {
        this.method = value;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients value) {
        this.ingredients = value;
    }

    public String[] getFoodPairing() {
        return foodPairing;
    }

    public void setFoodPairing(String[] value) {
        this.foodPairing = value;
    }

    public String getBrewersTips() {
        return brewersTips;
    }

    public void setBrewersTips(String value) {
        this.brewersTips = value;
    }

    public String getContributedBy() {
        return contributedBy;
    }

    public void setContributedBy(String value) {
        this.contributedBy = value;
    }
}