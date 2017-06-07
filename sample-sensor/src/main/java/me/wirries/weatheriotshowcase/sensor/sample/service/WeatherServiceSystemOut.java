package me.wirries.weatheriotshowcase.sensor.sample.service;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * A simple implementation of the weather service.
 * This implementation prints only the given values to the console.
 * Only for testing.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 07.06.2017
 */
@Component("SYSTEMOUT")
public class WeatherServiceSystemOut implements WeatherService {

    @Override
    public void send(final double latitude, final double longitude, final double temperature) {
        System.out.println(MessageFormat.format("Lat.: {0}, Lon.: {1}, Temperature: {2}", latitude, latitude, temperature));
    }

}
