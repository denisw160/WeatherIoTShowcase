package me.wirries.weatheriotshowcase.server.service;

import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessageEntity;
import me.wirries.weatheriotshowcase.server.repository.TemperatureMessageRepository;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TemperatureMessageRepository repository;

    @Autowired
    public ProcessingServiceImpl(final DozerBeanMapper mapper,
                                 final TemperatureMessageRepository repository) {

        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public void process(final TemperatureMessage message) {
        LOGGER.debug("Processing new message {}", message);
        final TemperatureMessageEntity entity = new TemperatureMessageEntity();
        entity.setIncoming(new Date());
        mapper.map(message, entity);
        repository.save(entity);
    }

}
