package me.wirries.weatheriotshowcase.sensor.sample.model;

import java.util.Arrays;
import java.util.List;

/**
 * This enum provides all supported interfaces for this client.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
public enum Interfaces {

    REST("REST API"),
    MQTT("MQTT API"),
    KAA("KAA Client API");

    private final String name;

    Interfaces(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static List<Interfaces> list() {
        return Arrays.asList(REST, MQTT, KAA);
    }

}
