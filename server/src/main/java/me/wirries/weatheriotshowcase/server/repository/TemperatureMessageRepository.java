package me.wirries.weatheriotshowcase.server.repository;

import me.wirries.weatheriotshowcase.server.model.TemperatureMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * This repository handles the access on the (incoming) messages for temperature.
 * See also {@link TemperatureMessageEntity}.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Repository
public interface TemperatureMessageRepository extends JpaRepository<TemperatureMessageEntity, Long> {

    /**
     * Return the count of the messages after the fromDate.
     *
     * @param fromDate Messages after this date
     * @return Count
     */
    @Query("select count(id) from TemperatureMessageEntity where incoming > :from")
    long countLastMessages(@Param("from") Date fromDate);

}
