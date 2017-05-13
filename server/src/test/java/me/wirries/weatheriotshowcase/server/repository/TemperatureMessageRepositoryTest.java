package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.TemperatureMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Test repository {@link TemperatureMessageRepository}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class TemperatureMessageRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private TemperatureMessageRepository repository;

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }

    @Override
    protected void initData() {
        clearData();
        for (int i = 0; i < 10; i++) {
            final TemperatureMessageEntity e = new TemperatureMessageEntity();
            e.setLatitude(i);
            e.setLongitude(i);
            e.setStationId("Station" + i);
            e.setTemperature(i);
            e.setIncoming(new Date());
            repository.save(e);
        }
    }

    @Override
    protected void clearData() {
        repository.deleteAll();
    }

}