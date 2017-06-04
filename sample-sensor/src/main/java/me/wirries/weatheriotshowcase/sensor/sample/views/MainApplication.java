package me.wirries.weatheriotshowcase.sensor.sample.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.wirries.weatheriotshowcase.sensor.sample.config.ApplicationProperties;
import me.wirries.weatheriotshowcase.sensor.sample.service.AliveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * This is the main stage of the application.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
public class MainApplication extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    private static ApplicationContext context;

    /**
     * Start the application.
     */
    public static void launch(final ApplicationContext ctx) {
        context = ctx;
        LOGGER.info("Starting JavaFX window ");
        launch();
    }

    /**
     * Return the properties of the application.
     *
     * @return ApplicationProperties
     */
    public static ApplicationProperties getProperties() {
        return getBean(ApplicationProperties.class);
    }

    /**
     * Return the bean of the give type.
     *
     * @param type Type of the Bean
     * @param <T>  Type
     * @return Bean
     */
    public static <T> T getBean(final Class<T> type) {
        return context.getBean(type);
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml"));
        final Parent root = loader.load();

        final MainController controller = loader.getController();
        controller.setAliveService(getBean(AliveService.class));

        final Scene scene = new Scene(root, getProperties().getWidth(), getProperties().getHeight());
        scene.getStylesheets().add(getClass().getResource("/style/main.css").toExternalForm());

        stage.setTitle(getProperties().getName());
        stage.setResizable(getProperties().isResizeable());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/weather-72x72.png")));

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(we -> {
            LOGGER.debug("Stage is closing - calling exit");
            System.exit(0);
        });

        LOGGER.info("{} {} started", getProperties().getName(), getProperties().getVersion());
    }

}
