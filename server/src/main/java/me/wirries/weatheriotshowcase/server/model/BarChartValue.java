package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * This class represented the format for the bar chart values.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class BarChartValue implements Serializable {

    private static final long serialVersionUID = -785304156445509954L;

    private List<Serializable[]> value;

    public List<Serializable[]> getValue() {
        return value;
    }

    public void setValue(final List<Serializable[]> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }

}
