package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This is the value for a weather station.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.17
 */
public class TemperatureValue {

    private double latitude;

    private double longitude;

    private double temperature;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("temperature", temperature)
                .toString();
    }

}
