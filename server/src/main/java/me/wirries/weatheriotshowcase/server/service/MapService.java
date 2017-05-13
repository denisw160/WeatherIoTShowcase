package me.wirries.weatheriotshowcase.server.service;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * This service handles the coordinates requests.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public interface MapService {

    /**
     * Translate the given coordinates into an address result.
     *
     * @param latLng Coordinates
     * @return Result with addresses
     */
    GeocodingResult translate(LatLng latLng);

}
