package dev.georgetech.beercatalog.beers.dto;

public class MashTemp {
    private BoilVolume temp;
    private long duration;

    public BoilVolume getTemp() {
        return temp;
    }

    public void setTemp(BoilVolume value) {
        this.temp = value;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long value) {
        this.duration = value;
    }
}