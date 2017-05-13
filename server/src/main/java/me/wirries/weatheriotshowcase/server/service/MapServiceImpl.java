package me.wirries.weatheriotshowcase.server.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * This implementation used Google Maps for translation.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class MapServiceImpl implements MapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapServiceImpl.class);

    @Value("${google.api.key}")
    private String apiKey;

    @Override
    public GeocodingResult translate(final LatLng latLng) {
        try {
            final GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
            final GeocodingResult[] results = GeocodingApi.reverseGeocode(context, latLng).await();

            if (results != null && results.length > 0) {
                return results[0];
            }

        } catch (final Exception e) {
            LOGGER.warn("Unable to locate address for " + latLng, e);
        }

        return null;
    }

}
