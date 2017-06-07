package me.wirries.weatheriotshowcase.sensor.sample.config;

import me.wirries.weatheriotshowcase.sensor.sample.views.MainApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

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
    @Description("Start the JAVAFX application")
    public ApplicationRunner startApplication(final ApplicationContext ctx) {
        return args -> {
            LOGGER.trace("Starting the JAVAFX application");
            MainApplication.launch(ctx);
        };
    }

    // *** MQTT Sender ***

    @Bean("mqttOutboundChannel")
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean("mqttClientFactory")
    public MqttPahoClientFactory mqttClientFactory(@Value("${mqtt.url}") final String url) {
        final DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs(url);
        // factory.setUserName("username");
        // factory.setPassword("password");
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(
            @Autowired @Qualifier("mqttClientFactory") final MqttPahoClientFactory mqttClientFactory,
            @Value("${mqtt.topic}") final String topic) {
        final MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("sample", mqttClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(topic);
        return messageHandler;
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MqttGateway {

        void sendToMqtt(String data);

    }

}
