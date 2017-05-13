package me.wirries.weatheriotshowcase.server.service;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for {@link LocationService}.
 */
public class LocationServiceTest extends AbstractApplicationTest {

    @Autowired
    private LocationService locationService;

    @Test
    public void translate() throws Exception {
        locationService.translate(1, 1);
    }

}