package me.wirries.weatheriotshowcase.server.service;

import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This is the default implementation for the {@link ProcessingService}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Service
public class ProcessingServiceImpl implements ProcessingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingServiceImpl.class);

    @Override
    public void process(final TemperatureMessage message) {
        LOGGER.debug("Processing new message {}", message);

    }

}
