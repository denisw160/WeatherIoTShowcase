package me.wirries.weatheriotshowcase.server.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This class stores a simple projection of name and count.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 13.05.2017
 */
public class SimpleCountGroup {

    private String name;
    private Long count;

    public SimpleCountGroup() {
        // default
    }

    public SimpleCountGroup(final String name, final Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(final Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("count", count)
                .toString();
    }

}
