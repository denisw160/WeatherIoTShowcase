package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This is the message from the weather station with a temperature
 * value.
 *
 * @author denisw
 * @version 1.0
 * @since 09.05.17
 */
public class TemperatureMessage {

    private String stationId;

    private double latitude;

    private double longitude;

    private double temperature;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(final String stationId) {
        this.stationId = stationId;
    }

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
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final TemperatureMessage that = (TemperatureMessage) o;

        return new EqualsBuilder()
                .append(stationId, that.stationId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(stationId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("stationId", stationId)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("temperature", temperature)
                .toString();
    }

}
