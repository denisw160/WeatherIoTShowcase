package me.wirries.weatheriotshowcase.sensor.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static me.wirries.weatheriotshowcase.sensor.sample.model.Temperature.convertToJson;

/**
 * This is the implementation for the REST API.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 07.06.2017
 */
@Component("REST")
public class WeatherServiceRest implements WeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceRest.class);

    @Value("${rest.url}")
    private String url;

    @Override
    public void send(final double latitude, final double longitude, final double temperature) {
        try {
            final String jsonObj = convertToJson(latitude, longitude, temperature);
            LOGGER.debug("Sending {} to {}", jsonObj, url);

            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            final HttpEntity<String> entity = new HttpEntity<>(jsonObj, headers);
            final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            final ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);

            LOGGER.debug("Done, Response: {}", response.getStatusCode());

        } catch (final Exception e) {
            LOGGER.error("Sending failed", e);
        }
    }

}
