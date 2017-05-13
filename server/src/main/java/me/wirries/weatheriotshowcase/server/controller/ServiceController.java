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
 * Controller for the REST API.
 *
 * @author Denis.Wirries
 * @version 1.0
 * @since 06.05.2017
 */
@RestController
public class ServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ProcessingService processingService;

    /**
     * This is an example for a RESTful service. The service return
     * "sample" as String.
     *
     * @return a String
     */
    @ApiOperation(value = "This is an example for a RESTful service. The service return 'sample' as String.")
    @RequestMapping(value = "/rest/sample", method = RequestMethod.GET)
    public String sample() {
        LOGGER.debug("Calling sample rest service");
        return "sample";
    }

    /**
     * This services stores the temperature message and updates the system.
     *
     * @param message The message with the temperature value.
     */
    @ApiOperation(value = "This services stores the temperature message and updates the system.")
    @RequestMapping(value = "/rest/temperature", method = RequestMethod.POST)
    public void temperature(final TemperatureMessage message) {
        LOGGER.debug("Receiving new temperature message {}", message);
        processingService.process(message);
    }

}
