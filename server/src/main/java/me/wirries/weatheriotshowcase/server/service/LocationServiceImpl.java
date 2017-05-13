package me.wirries.weatheriotshowcase.server.service;

import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.LocationEntity;
import me.wirries.weatheriotshowcase.server.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.ArrayUtils.contains;

/**
 * Implementation of the {@link LocationService}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository locationRepository;
    private final MapService mapService;

    @Autowired
    public LocationServiceImpl(final MapService mapService,
                               final LocationRepository locationRepository) {
        this.mapService = mapService;
        this.locationRepository = locationRepository;
    }

    @Override
    public void translate(final double latitude, final double longitude) {
        final LocationEntity entity = createOrFindLocation(latitude, longitude);

        if (entity.getName() == null) {
            LOGGER.debug("Creating cache for coordinates lat: {} and lng: {}", latitude, longitude);
            try {
                final LatLng location = new LatLng(latitude, longitude);
                final GeocodingResult result = mapService.translate(location);

                if (result != null) {
                    updateLocation(entity, result);
                    locationRepository.save(entity);
                }
            } catch (final Exception e) {
                LOGGER.warn("Unable to locate address for " + latitude + " / " + longitude, e);
            }
        }
    }

    private LocationEntity createOrFindLocation(final double latitude, final double longitude) {
        final CoordinateId id = new CoordinateId(latitude, longitude);
        LocationEntity entity = locationRepository.findOne(id);
        if (entity == null) {
            entity = new LocationEntity();
            entity.setId(id);
        }

        return entity;
    }

    private void updateLocation(final LocationEntity entity, final GeocodingResult result) {
        entity.setName(result.formattedAddress);
        entity.setSubLocalityLevel1(getValue(result, AddressComponentType.SUBLOCALITY_LEVEL_1));
        entity.setSubLocalityLevel2(getValue(result, AddressComponentType.SUBLOCALITY_LEVEL_2));
        entity.setSubLocalityLevel3(getValue(result, AddressComponentType.SUBLOCALITY_LEVEL_3));

        entity.setLocality(getValue(result, AddressComponentType.LOCALITY));

        entity.setAdministrativeLevel1(getValue(result, AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1));
        entity.setAdministrativeLevel2(getValue(result, AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2));
        entity.setAdministrativeLevel3(getValue(result, AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_3));

        entity.setPostalCode(getValue(result, AddressComponentType.POSTAL_CODE));

        entity.setCountry(getValue(result, AddressComponentType.COUNTRY));
        entity.setIsoCode(getValue(result, AddressComponentType.COUNTRY, true));
    }

    private String getValue(final GeocodingResult result, final AddressComponentType type) {
        return getValue(result, type, false);
    }

    private String getValue(final GeocodingResult result, final AddressComponentType type, final boolean shortName) {
        for (final AddressComponent a : result.addressComponents) {
            if (contains(a.types, type)) {
                return shortName ? a.shortName : a.longName;
            }
        }
        return null;
    }

}
