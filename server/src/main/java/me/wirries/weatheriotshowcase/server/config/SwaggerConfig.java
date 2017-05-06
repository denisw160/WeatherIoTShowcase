package me.wirries.weatheriotshowcase.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for the swapper api.
 *
 * @author Denis.Wirries
 * @version 1.0
 * @since 06.05.2017
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    @Description("This bean configure the documentation of the rest services")
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.wirries.weatheriotshowcase.server.controller"))
                .paths(PathSelectors.ant("/rest/**"))
                .build();
    }

}
