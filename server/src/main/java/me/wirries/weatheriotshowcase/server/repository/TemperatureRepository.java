package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.CoordinateId;
import me.wirries.weatheriotshowcase.server.model.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
     * Return all active temperature stations.
     *
     * @param fromDate Only station with data after fromDate
     * @return List of stations
     */
    @Query("select t from TemperatureEntity t where t.lastUpdate > :from")
    List<TemperatureEntity> findActive(@Param("from") Date fromDate);

    /**
     * Counts the stations with data after the fromDate.
     *
     * @param fromDate count only stations with data after this date
     * @return count
     */
    @Query("select count(stationId) from TemperatureEntity where lastUpdate > :from")
    long countActiveStations(@Param("from") Date fromDate);

    /**
     * Return the count of active stations group by location.
     * Return Type is Object[0] = name, Object[1] = count.
     *
     * @param fromDate count only stations with data after this date
     * @return count group by location
     */
    @Query("select e.administrativeLevel3 as name, count(t.stationId) as count from TemperatureEntity t, LocationEntity e where t.id=e.id and t.lastUpdate > :from group by e.administrativeLevel3")
    List<Object[]> countActiveStationsByLocation(@Param("from") Date fromDate);

    /**
     * Return the count of active stations group by country.
     * Return Type is Object[0] = name, Object[1] = count.
     *
     * @param fromDate count only stations with data after this date
     * @return count group by location
     */
    @Query("select lower(e.isoCode) as name, count(t.stationId) as count from TemperatureEntity t, LocationEntity e where t.id=e.id and t.lastUpdate > :from group by e.isoCode")
    List<Object[]> countActiveStationsByCountry(@Param("from") Date fromDate);

}
