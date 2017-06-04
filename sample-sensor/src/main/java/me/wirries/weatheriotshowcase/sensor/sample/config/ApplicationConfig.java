package me.wirries.weatheriotshowcase.sensor.sample.config;

import me.wirries.weatheriotshowcase.sensor.sample.views.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.Arrays;

/**
 * Configuration for the application.
 *
 * @author Denis.Wirries
 * @version 1.0
 * @since 04.06.2017
 */
@Configuration
public class ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    /**
     * Tracing the spring beans.
     */
    @Bean
    @Description("Tracing for  beans")
    public CommandLineRunner tracingBeans(final ApplicationContext ctx) {
        return args -> {
            LOGGER.trace("This beans provided by Spring Boot:");
            final String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (final String beanName : beanNames) {
                LOGGER.trace("  -> {}", beanName);
            }
        };
    }

    /**
     * Start the JAVAFX application.
     */
    @Bean
    @Description("Start the main window of the application")
    public ApplicationRunner startApplication(final ApplicationContext ctx, final ApplicationProperties props) {
        return args -> {
            LOGGER.trace("");
            MainWindow.run(ctx, props);
        };
    }

}
