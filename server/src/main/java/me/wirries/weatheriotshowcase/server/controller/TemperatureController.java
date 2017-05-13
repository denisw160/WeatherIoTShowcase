package me.wirries.weatheriotshowcase.server.controller;

import io.swagger.annotations.ApiOperation;
import me.wirries.weatheriotshowcase.server.config.ApplicationConfig;
import me.wirries.weatheriotshowcase.server.model.BarChartValue;
import me.wirries.weatheriotshowcase.server.model.GaugeChartValue;
import me.wirries.weatheriotshowcase.server.repository.TemperatureMessageRepository;
import me.wirries.weatheriotshowcase.server.repository.TemperatureRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This controller handles the requests for the temperatures.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@RestController
public class TemperatureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    private final TemperatureRepository repository;
    private final TemperatureMessageRepository messageRepository;

    private long maxValueStations = 0;
    private long maxValueIncoming = 0;

    @Autowired
    public TemperatureController(final TemperatureRepository repository,
                                 final TemperatureMessageRepository messageRepository) {

        this.repository = repository;
        this.messageRepository = messageRepository;
    }

    @ApiOperation(value = "This service return the count of the active stations.")
    @RequestMapping(value = "/rest/temperature/stations", method = RequestMethod.GET)
    public GaugeChartValue stations() {
        LOGGER.debug("Searching for active stations");

        final Date fromDate = DateUtils.addMinutes(new Date(), -5);
        final long count = repository.countActiveStations(fromDate);
        maxValueStations = Math.max(count, maxValueStations);

        return createGaugeValue(count, maxValueStations);
    }

    @ApiOperation(value = "This service return the count of last received messages.")
    @RequestMapping(value = "/rest/temperature/received", method = RequestMethod.GET)
    public GaugeChartValue received() {
        LOGGER.debug("Searching for last received messages");

        final Date fromDate = DateUtils.addMinutes(new Date(), -1);
        final long count = messageRepository.countLastMessages(fromDate);
        maxValueIncoming = Math.max(count, maxValueIncoming);

        return createGaugeValue(count, maxValueIncoming);
    }

    private GaugeChartValue createGaugeValue(final long count, final long maxValueStations) {
        final GaugeChartValue value = new GaugeChartValue();
        value.setValue(count);
        value.setMin(0);
        value.setMax(maxValueStations);
        return value;
    }

    @ApiOperation(value = "This service return the count of the active stations group by location.")
    @RequestMapping(value = "/rest/temperature/locations", method = RequestMethod.GET)
    public BarChartValue locations() {
        LOGGER.debug("Searching for active stations by location");

        final Date fromDate = DateUtils.addMinutes(new Date(), -5);
        final List<Serializable[]> count = new ArrayList<>(); // repository.countActiveStationsByLocation(fromDate);
        count.add(new Serializable[]{"t1", 3});
        count.add(new Serializable[]{"t2", 1});
        count.add(new Serializable[]{"t3", 2});

        return createBarValue(count);
    }

    private BarChartValue createBarValue(final List<Serializable[]> count) {
        final BarChartValue value = new BarChartValue();
        value.setValue(count);

        return value;
    }

}
