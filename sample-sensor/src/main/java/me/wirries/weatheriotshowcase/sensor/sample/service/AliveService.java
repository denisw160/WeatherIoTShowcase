package me.wirries.weatheriotshowcase.sensor.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * This service checks if the server is reachable.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
@Component
public class AliveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliveService.class);

    @Value("${server.ipAddress}")
    private String ipAddress;

    public boolean isReachable() {
        LOGGER.debug("Checking if server {} is reachable", ipAddress);

        try {
            final InetAddress inet = InetAddress.getByName(ipAddress);
            final boolean reachable = inet.isReachable(5000);

            LOGGER.debug(reachable ? "Host is reachable" : "Host is NOT reachable");
            return reachable;

        } catch (final Exception e) {
            LOGGER.warn("Error while checking server", e);
        }

        return false;
    }

}
