package dev.georgetech.beercatalog.beers.dto;

public class Hop {
    private String name;
    private BoilVolume amount;
    private String add;
    private String attribute;

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

    public String getAdd() {
        return add;
    }

    public void setAdd(String value) {
        this.add = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String value) {
        this.attribute = value;
    }
}