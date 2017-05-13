package me.wirries.weatheriotshowcase.server.service;

import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;

/**
 * This Service processed the incoming messages.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public interface ProcessingService {

    /**
     * Process the message and store in the database.
     * If the location is unknown, the location will be lookup.
     *
     * @param message the incoming message
     */
    void process(TemperatureMessage message);

}
