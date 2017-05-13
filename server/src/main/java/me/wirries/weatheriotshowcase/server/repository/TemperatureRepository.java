package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * This repository handles the access for the temperatures.
 * See also {@link TemperatureEntity}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Repository
public interface TemperatureRepository extends JpaRepository<TemperatureEntity, CoordinateId> {

    /**
     * Counts the stations with data after the fromDate.
     *
     * @param fromDate count only stations with data after this date
     * @return count
     */
    @Query("select count(stationId) from TemperatureEntity where lastUpdate > :from")
    long countActiveStations(@Param("from") Date fromDate);

//    long countActiveStationsByLocation(Date fromDate);

}
