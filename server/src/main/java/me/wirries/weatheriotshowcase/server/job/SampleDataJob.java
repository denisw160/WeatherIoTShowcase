package me.wirries.weatheriotshowcase.server.job;

import me.wirries.weatheriotshowcase.server.controller.MessageController;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class creates sample data.
 *
 * @author denisw
 * @version 1.0
 * @since 09.05.17
 */
@Component
public class SampleDataJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataJob.class);

    private final MessageController service;

    @Value("${job.sampleData}")
    private boolean active;

    @Autowired
    public SampleDataJob(final MessageController service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 15000)
    public void run() {
        if (!active) return;

        createData();
    }

    public void createData() {
        LOGGER.debug("Creating sampling data");
        for (int i = 0; i < 10; i++) {
            final Random random = new Random();
            final int r = random.nextInt(10);
            if (r < 6) continue;

            final TemperatureMessage message = new TemperatureMessage();
            message.setStationId("station-" + i);
            getLocation(message, 52.376270, 9.739498, 10000);

            final double temp = ThreadLocalRandom.current().nextDouble(-10.0, 40);
            message.setTemperature(temp);

            service.temperature(message);
        }
    }

    private static void getLocation(final TemperatureMessage message,
                                    final double x0, final double y0, final int radius) {
        final Random random = new Random();

        // Convert radius from meters to degrees
        final double radiusInDegrees = radius / 111000f;

        final double u = random.nextDouble();
        final double v = random.nextDouble();
        final double w = radiusInDegrees * Math.sqrt(u);
        final double t = 2 * Math.PI * v;
        final double x = w * Math.cos(t);
        final double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        final double new_x = x / Math.cos(Math.toRadians(y0));

        final double foundLongitude = new_x + x0;
        final double foundLatitude = y + y0;

        message.setLatitude(foundLongitude);
        message.setLongitude(foundLatitude);
    }

}
