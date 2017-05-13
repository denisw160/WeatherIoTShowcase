package me.wirries.weatheriotshowcase.server.service;

/**
 * This service translate the geo coordinates into a location.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public interface LocationService {

    /**
     * Adds this coordinates to the location cache.
     *
     * @param latitude  Coordinates
     * @param longitude Coordinates
     */
    void translate(double latitude, double longitude);

}
