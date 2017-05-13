package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * This Entity store the information for an location
 * given by long. and lat.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
@Entity
@Table(name = "location")
public class LocationEntity implements Serializable {

    private static final long serialVersionUID = 2031426599815663549L;

    @EmbeddedId
    private CoordinateId id;

    private String name;

    private String hcA2;

    private String county;

    public CoordinateId getId() {
        return id;
    }

    public void setId(final CoordinateId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getHcA2() {
        return hcA2;
    }

    public void setHcA2(final String hcA2) {
        this.hcA2 = hcA2;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(final String county) {
        this.county = county;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final LocationEntity that = (LocationEntity) o;

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
                .append("name", name)
                .append("hcA2", hcA2)
                .append("county", county)
                .toString();
    }
}
