package me.wirries.weatheriotshowcase.server.config;

import me.wirries.weatheriotshowcase.server.service.MapService;
import me.wirries.weatheriotshowcase.server.service.MapServiceMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * This is the configuration for the tests.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Configuration
public class TestConfig extends ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    @Override
    public MapService mapService() {
        LOGGER.debug("Using MockUpService as MapService");
        return new MapServiceMock();
    }

}
