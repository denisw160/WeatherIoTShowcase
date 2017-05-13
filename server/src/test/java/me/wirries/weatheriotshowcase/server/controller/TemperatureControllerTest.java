package me.wirries.weatheriotshowcase.server.controller;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import me.wirries.weatheriotshowcase.server.model.GaugeChartValue;
import me.wirries.weatheriotshowcase.server.model.SimpleKeyValue;
import me.wirries.weatheriotshowcase.server.model.TemperatureValue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for {@link TemperatureController}.
 */
public class TemperatureControllerTest extends AbstractApplicationTest {

    @Autowired
    private TemperatureController controller;

    @Test
    public void temperature() throws Exception {
        final List<TemperatureValue> temperatureValues = controller.temperature();
        assertNotNull(temperatureValues);
    }

    @Test
    public void stations() throws Exception {
        final GaugeChartValue stations = controller.stations();
        assertNotNull(stations);
    }

    @Test
    public void received() throws Exception {
        final GaugeChartValue received = controller.received();
        assertNotNull(received);
    }

    @Test
    public void locations() throws Exception {
        final SimpleKeyValue locations = controller.locations();
        assertNotNull(locations);
    }

    @Test
    public void locationsCountry() throws Exception {
        final SimpleKeyValue simpleKeyValue = controller.locationsCountry();
        assertNotNull(simpleKeyValue);
    }

}