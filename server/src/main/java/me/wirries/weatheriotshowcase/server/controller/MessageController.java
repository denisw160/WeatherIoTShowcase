package me.wirries.weatheriotshowcase.server.controller;

import io.swagger.annotations.ApiOperation;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import me.wirries.weatheriotshowcase.server.service.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles incoming messages.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@RestController
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private final ProcessingService processingService;

    @Autowired
    public MessageController(final ProcessingService processingService) {
        this.processingService = processingService;
    }

    /**
     * This services stores the temperature message and updates the system.
     *
     * @param message The message with the temperature value.
     */
    @ApiOperation(value = "This services stores the temperature message and updates the system.")
    @RequestMapping(value = "/rest/message/temperature", method = RequestMethod.POST)
    public void temperature(final TemperatureMessage message) {
        LOGGER.debug("Receiving new temperature message {}", message);
        processingService.process(message);
    }

}
