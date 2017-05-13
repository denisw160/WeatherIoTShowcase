package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * This is the message from the weather station with a temperature
 * value.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.17
 */
@Entity
@Table(name = "temperature")
public class TemperatureEntity {

    @EmbeddedId
    private CoordinateId id;

    private String stationId;

    private double temperature;

    private Date lastUpdate;

    public CoordinateId getId() {
        return id;
    }

    public void setId(final CoordinateId id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(final String stationId) {
        this.stationId = stationId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final TemperatureEntity that = (TemperatureEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("stationId", stationId)
                .append("temperature", temperature)
                .append("lastUpdate", lastUpdate)
                .toString();
    }

}
