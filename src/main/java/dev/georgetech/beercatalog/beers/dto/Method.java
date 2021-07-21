package dev.georgetech.beercatalog.beers.dto;

import lombok.ToString;

@ToString
public class Method {
    private MashTemp[] mashTemp;
    private Fermentation fermentation;
    private Object twist;

    public MashTemp[] getMashTemp() {
        return mashTemp;
    }

    public void setMashTemp(MashTemp[] value) {
        this.mashTemp = value;
    }

    public Fermentation getFermentation() {
        return fermentation;
    }

    public void setFermentation(Fermentation value) {
        this.fermentation = value;
    }

    public Object getTwist() {
        return twist;
    }

    public void setTwist(Object value) {
        this.twist = value;
    }
}