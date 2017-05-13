package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This is a composite key.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Embeddable
public class CoordinateId implements Serializable {

    private static final long serialVersionUID = -6572875403543796227L;

    private double latitude;

    private double longitude;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final CoordinateId that = (CoordinateId) o;

        return new EqualsBuilder()
                .append(latitude, that.latitude)
                .append(longitude, that.longitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(latitude)
                .append(longitude)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .toString();
    }
}
