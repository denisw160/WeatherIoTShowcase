package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * This class represented a value in a gauge chart.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class GaugeChartValue implements Serializable {

    private static final long serialVersionUID = 8775854680148435604L;

    private double min;
    private double max;

    private double value;

    public double getMin() {
        return min;
    }

    public void setMin(final double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(final double max) {
        this.max = max;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("min", min)
                .append("max", max)
                .append("value", value)
                .toString();
    }

}
