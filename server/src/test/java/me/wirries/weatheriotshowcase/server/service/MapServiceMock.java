package me.wirries.weatheriotshowcase.server.service;

import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a mockup implementation of the map service.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class MapServiceMock implements MapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapServiceMock.class);

    @Override
    public GeocodingResult translate(final LatLng latLng) {
        LOGGER.info("Return MockUp result");
        final GeocodingResult result = new GeocodingResult();
        result.formattedAddress = "Test";
        result.addressComponents = new AddressComponent[1];
        result.addressComponents[0] = new AddressComponent();
        result.addressComponents[0].shortName = "T";
        result.addressComponents[0].longName = "Test";
        result.addressComponents[0].types = new AddressComponentType[]{AddressComponentType.SUBLOCALITY};

        return result;
    }

}
