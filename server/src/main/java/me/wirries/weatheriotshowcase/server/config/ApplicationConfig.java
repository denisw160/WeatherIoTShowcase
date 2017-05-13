package me.wirries.weatheriotshowcase.server.config;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * Configuration for the web application.
 *
 * @author Denis.Wirries
 * @version 1.0
 * @since 06.05.2017
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    @Description("Tracing for spring beans")
    public CommandLineRunner tracingBeans(final ApplicationContext ctx) {
        return args -> {
            LOGGER.trace("Let's inspect the beans provided by Spring Boot:");

            final String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (final String beanName : beanNames) {
                LOGGER.trace("  -> {}", beanName);
            }
        };
    }

    @Bean(name = "org.dozer.Mapper")
    @Description("Create Mapper for POJO transformation")
    public DozerBeanMapper dozerBean() {
        return new DozerBeanMapper();
    }

}
