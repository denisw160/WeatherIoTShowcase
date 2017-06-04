package me.wirries.weatheriotshowcase.sensor.sample.model;

import java.util.Arrays;
import java.util.List;

/**
 * This enum contains the locations.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
public enum Locations {

    HANNOVER("Hannover", 52.375922, 9.740373),
    WOLFSBURG("Wolfsburg", 52.432622, 10.802373),
    CUSTOM("Eingabe", 52.0, 10.0);

    private final String name;
    private final double lat;
    private final double lon;

    Locations(final String name, final double lat, final double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static List<Locations> list() {
        return Arrays.asList(HANNOVER, WOLFSBURG, CUSTOM);
    }

}
