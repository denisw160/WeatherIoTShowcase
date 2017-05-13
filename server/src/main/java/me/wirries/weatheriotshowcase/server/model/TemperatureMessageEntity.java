package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "temperature_message")
public class TemperatureMessageEntity implements Serializable {

    private static final long serialVersionUID = 1111331466650639361L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String stationId;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double temperature;

    @Column(nullable = false)
    private Date incoming;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(final String stationId) {
        this.stationId = stationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    public Date getIncoming() {
        return incoming;
    }

    public void setIncoming(final Date incoming) {
        this.incoming = incoming;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final TemperatureMessageEntity that = (TemperatureMessageEntity) o;

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
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("temperature", temperature)
                .append("incoming", incoming)
                .toString();
    }

}
