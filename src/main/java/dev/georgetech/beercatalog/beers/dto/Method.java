package dev.georgetech.beercatalog.beers.dto;

import lombok.Data;

@Data
public class Method {
    private MashTemp[] mashTemp;
    private Fermentation fermentation;
    private Object twist;

}