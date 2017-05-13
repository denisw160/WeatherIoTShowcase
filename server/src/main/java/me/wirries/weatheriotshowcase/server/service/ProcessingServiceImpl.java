package me.wirries.weatheriotshowcase.server.service;

import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.TemperatureEntity;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessageEntity;
import me.wirries.weatheriotshowcase.server.repository.TemperatureMessageRepository;
import me.wirries.weatheriotshowcase.server.repository.TemperatureRepository;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

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

    private final DozerBeanMapper mapper;
    private final TemperatureRepository temperatureRepository;
    private final TemperatureMessageRepository messageRepository;
    private final LocationService locationService;

    @Autowired
    public ProcessingServiceImpl(final DozerBeanMapper mapper,
                                 final TemperatureRepository temperatureRepository,
                                 final TemperatureMessageRepository messageRepository,
                                 final LocationService locationService) {

        this.mapper = mapper;
        this.temperatureRepository = temperatureRepository;
        this.messageRepository = messageRepository;
        this.locationService = locationService;
    }

    @Override
    @Transactional
    public void process(final TemperatureMessage message) {
        LOGGER.debug("Processing new message {}", message);
        storeMessage(message);

        updateValue(message);
        updateLocation(message);
    }

    private void storeMessage(final TemperatureMessage message) {
        final TemperatureMessageEntity entity = new TemperatureMessageEntity();
        mapper.map(message, entity);
        entity.setIncoming(new Date());

        LOGGER.debug("Storing new message {}", message);
        messageRepository.save(entity);
    }

    @Async
    private void updateValue(final TemperatureMessage message) {
        final TemperatureEntity entity = createOrFindTemperature(message.getLatitude(), message.getLongitude());
        mapper.map(message, entity);
        entity.setLastUpdate(new Date());

        LOGGER.debug("Create or Update temperature value {}", message);
        temperatureRepository.save(entity);
    }

    private TemperatureEntity createOrFindTemperature(final double latitude, final double longitude) {
        final CoordinateId id = new CoordinateId(latitude, longitude);
        TemperatureEntity entity = temperatureRepository.findOne(id);
        if (entity == null) {
            entity = new TemperatureEntity();
            entity.setId(id);
        }

        return entity;
    }

    @Async
    private void updateLocation(final TemperatureMessage message) {
        locationService.cache(message.getLatitude(), message.getLongitude());
    }

}
