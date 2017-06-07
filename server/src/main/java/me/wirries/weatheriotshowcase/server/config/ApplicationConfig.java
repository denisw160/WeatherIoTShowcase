package me.wirries.weatheriotshowcase.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wirries.weatheriotshowcase.server.controller.MessageController;
import me.wirries.weatheriotshowcase.server.model.TemperatureMessage;
import me.wirries.weatheriotshowcase.server.service.MapService;
import me.wirries.weatheriotshowcase.server.service.MapServiceImpl;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
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

    @Bean
    @Description("Creates the bean for the map service")
    public MapService mapService() {
        LOGGER.debug("Using GoogleMaps as MapService");
        return new MapServiceImpl();
    }

    // *** MQTT Client ***

    @Bean("mqttInputChannel")
    @Description("Input from the MQTT channel")
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @Description("The channel with weather data")
    public MessageProducer inboundWeatherData(
            @Autowired @Qualifier("mqttInputChannel") final MessageChannel messageChannel,
            @Value("${mqtt.url}") final String url,
            @Value("${mqtt.topic}") final String topic) {
        final MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        url,
                        "server",
                        topic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(messageChannel);
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    @Description("Handler for the weather data")
    public MessageHandler handler(@Autowired final MessageController controller) {
        return message -> {
            final String payload = String.valueOf(message.getPayload());
            LOGGER.debug("Received MQTT message: {}", payload);

            final ObjectMapper mapper = new ObjectMapper();
            try {
                final TemperatureMessage temperatureMessage = mapper.readValue(payload, TemperatureMessage.class);
                controller.temperature(temperatureMessage);
            } catch (final IOException e) {
                LOGGER.warn("Message '" + payload + "' is not a TemperatureMessage", e);
            }
        };
    }

}
