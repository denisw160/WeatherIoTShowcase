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

    private String subLocalityLevel1;
    private String subLocalityLevel2;
    private String subLocalityLevel3;

    private String locality;

    private String administrativeLevel3;
    private String administrativeLevel2;
    private String administrativeLevel1;

    private String postalCode;

    private String country;
    private String isoCode;


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

    public String getSubLocalityLevel1() {
        return subLocalityLevel1;
    }

    public void setSubLocalityLevel1(final String subLocalityLevel1) {
        this.subLocalityLevel1 = subLocalityLevel1;
    }

    public String getSubLocalityLevel2() {
        return subLocalityLevel2;
    }

    public void setSubLocalityLevel2(final String subLocalityLevel2) {
        this.subLocalityLevel2 = subLocalityLevel2;
    }

    public String getSubLocalityLevel3() {
        return subLocalityLevel3;
    }

    public void setSubLocalityLevel3(final String subLocalityLevel3) {
        this.subLocalityLevel3 = subLocalityLevel3;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(final String locality) {
        this.locality = locality;
    }

    public String getAdministrativeLevel3() {
        return administrativeLevel3;
    }

    public void setAdministrativeLevel3(final String administrativeLevel3) {
        this.administrativeLevel3 = administrativeLevel3;
    }

    public String getAdministrativeLevel2() {
        return administrativeLevel2;
    }

    public void setAdministrativeLevel2(final String administrativeLevel2) {
        this.administrativeLevel2 = administrativeLevel2;
    }

    public String getAdministrativeLevel1() {
        return administrativeLevel1;
    }

    public void setAdministrativeLevel1(final String administrativeLevel1) {
        this.administrativeLevel1 = administrativeLevel1;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
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
                .append("subLocalityLevel1", subLocalityLevel1)
                .append("subLocalityLevel2", subLocalityLevel2)
                .append("subLocalityLevel3", subLocalityLevel3)
                .append("locality", locality)
                .append("administrativeLevel3", administrativeLevel3)
                .append("administrativeLevel2", administrativeLevel2)
                .append("administrativeLevel1", administrativeLevel1)
                .append("postalCode", postalCode)
                .append("country", country)
                .append("isoCode", isoCode)
                .toString();
    }

}
