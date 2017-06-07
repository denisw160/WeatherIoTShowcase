package me.wirries.weatheriotshowcase.sensor.sample.service;

import me.wirries.weatheriotshowcase.sensor.sample.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static me.wirries.weatheriotshowcase.sensor.sample.model.Temperature.convertToJson;

/**
 * This is the implementation for the MQTT sender.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 07.06.2017
 */
@Component("MQTT")
public class WeatherServiceMqtt implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceMqtt.class);

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ApplicationConfig.MqttGateway gateway;

    @Override
    public void send(final double latitude, final double longitude, final double temperature) {
        try {
            final String jsonObj = convertToJson(latitude, longitude, temperature);
            LOGGER.debug("Sending {} to mqtt", jsonObj);

            gateway.sendToMqtt(jsonObj);
            LOGGER.debug("Done");

        } catch (final Exception e) {
            LOGGER.error("Sending failed", e);
        }
    }

}
