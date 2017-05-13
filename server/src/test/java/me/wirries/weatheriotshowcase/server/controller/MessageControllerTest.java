package me.wirries.weatheriotshowcase.server.controller;

import me.wirries.weatheriotshowcase.server.AbstractApplicationTest;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for {@link MessageController}.
 */
public class MessageControllerTest extends AbstractApplicationTest {

    @Autowired
    private MessageController controller;

    @Test
    public void temperature() throws Exception {
        final TemperatureMessage message = new TemperatureMessage();
        message.setLatitude(1);
        message.setLongitude(1);
        message.setStationId("Station1");
        message.setTemperature(1.0);
        controller.temperature(message);
    }

}