package me.wirries.weatheriotshowcase.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.Arrays;

/**
 * Main-Class for staring Spring Boot Application.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 06.05.2017
 */
@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(ServerApplication.class);

    /**
     * Starting the application.
     *
     * @param args Arguments from command line
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        final String arguments = Arrays.toString(args);
        LOG.info("Spring Boot Application started with {} ...", arguments);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServerApplication.class);
    }

}
