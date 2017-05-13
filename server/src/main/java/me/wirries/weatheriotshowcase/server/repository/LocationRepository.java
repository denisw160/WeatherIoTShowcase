package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository handles the access on the locations.
 * See also {@link LocationEntity}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, CoordinateId> {

}
