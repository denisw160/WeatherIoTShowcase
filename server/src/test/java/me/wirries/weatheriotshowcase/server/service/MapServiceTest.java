package me.wirries.weatheriotshowcase.server.service;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link MapService}.
 */
public class MapServiceTest extends AbstractApplicationTest {

    @Autowired
    private MapService mapService;

    @Test
    public void translate() throws Exception {
        final GeocodingResult translate = mapService.translate(new LatLng(1, 1));
        assertNotNull(translate.formattedAddress);
        assertNotNull(translate.addressComponents);
    }

}