package me.wirries.weatheriotshowcase.sensor.sample.service;

/**
 * This interface defines the service for sending the weather data to the weather platform.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 07.06.2017
 */
public interface WeatherService {

    /**
     * Send the temperature to the server.
     *
     * @param latitude    latitude
     * @param longitude   longitude
     * @param temperature temperature
     */
    void send(double latitude, double longitude, double temperature);

}
