package me.wirries.weatheriotshowcase.server.service;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for {@link ProcessingService}.
 */
public class ProcessingServiceImplTest extends AbstractApplicationTest {

    @Autowired
    private ProcessingService service;

    @Test
    public void process() throws Exception {
        final TemperatureMessage message = new TemperatureMessage();
        message.setLatitude(1);
        message.setLongitude(1);
        message.setStationId("Station1");
        message.setTemperature(1.0);
        service.process(message);
    }

}