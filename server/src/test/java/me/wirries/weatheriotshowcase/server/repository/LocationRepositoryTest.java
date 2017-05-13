package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Test repository {@link LocationRepository}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class LocationRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private LocationRepository repository;

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }

    @Override
    protected void initData() {
        clearData();
        for (int i = 0; i < 10; i++) {
            final CoordinateId id = new CoordinateId(i, i);
            final LocationEntity e = new LocationEntity();
            e.setId(id);
            repository.save(e);
        }
    }

    @Override
    protected void clearData() {
        repository.deleteAll();
    }

}