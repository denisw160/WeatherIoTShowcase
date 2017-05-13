package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.TemperatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Test repository {@link TemperatureRepository}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class TemperatureRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private TemperatureRepository repository;

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }

    @Override
    protected void initData() {
        clearData();
        for (int i = 0; i < 10; i++) {
            final CoordinateId id = new CoordinateId(i, i);
            final TemperatureEntity e = new TemperatureEntity();
            e.setId(id);
            e.setStationId("station" + i);
            e.setTemperature(i);
            e.setLastUpdate(new Date());
            repository.save(e);
        }
    }

    @Override
    protected void clearData() {
        repository.deleteAll();
    }

}