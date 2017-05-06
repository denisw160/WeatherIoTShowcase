package me.wirries.weatheriotshowcase.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.Arrays;

/**
 * Configuration for the web application.
 *
 * @author Denis.Wirries
 * @version 1.0
 * @since 06.05.2017
 */
@Configuration
public class ApplicationConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    @Description("Tracing for spring beans")
    public CommandLineRunner tracingBeans(ApplicationContext ctx) {
        return args -> {
            LOG.trace("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                LOG.trace("  -> {}", beanName);
            }
        };
    }

}