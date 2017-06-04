package me.wirries.weatheriotshowcase.sensor.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class provides the properties of the application.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
@Component
public class ApplicationProperties {

    @Value("${spring.application.name}")
    private String name;
    @Value("${spring.application.version}")
    private String version;

    @Value("${window.width}")
    private double width;
    @Value("${window.height}")
    private double height;
    @Value("${window.resizeable}")
    private boolean resizeable;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean isResizeable() {
        return resizeable;
    }

}
