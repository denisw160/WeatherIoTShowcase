package me.wirries.weatheriotshowcase.sensor.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * This is the main class of the sensor.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * Starting the application.
     *
     * @param args Arguments from command line
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
        final String arguments = Arrays.toString(args);
        LOGGER.info("Spring Boot Application started with {} ...", arguments);
    }

}