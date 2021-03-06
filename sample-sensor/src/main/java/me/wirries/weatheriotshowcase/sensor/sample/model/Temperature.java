package me.wirries.weatheriotshowcase.sensor.sample.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This is the class for the API.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 07.06.2017
 */
public class Temperature {

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
    public String toString() {
        return new ToStringBuilder(this)
                .append("stationId", stationId)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("temperature", temperature)
                .toString();
    }

    public static String convertToJson(final double latitude,
                                       final double longitude,
                                       final double temperature) throws JsonProcessingException {

        final Temperature obj = new Temperature();
        obj.setStationId("sample");
        obj.setLatitude(latitude);
        obj.setLongitude(longitude);
        obj.setTemperature(temperature);

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
