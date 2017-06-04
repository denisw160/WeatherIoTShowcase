package me.wirries.weatheriotshowcase.sensor.sample.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.wirries.weatheriotshowcase.sensor.sample.config.ApplicationProperties;
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
public class MainWindow extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainWindow.class);

    private static ApplicationContext context;
    private static ApplicationProperties properties;

    /**
     * Start the application.
     */
    public static void run(final ApplicationContext ctx, final ApplicationProperties props) {
        context = ctx;
        properties = props;
        LOGGER.info("Starting JavaFX window ");
        launch();
    }

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
        final Scene scene = new Scene(root, properties.getWidth(), properties.getHeight());
        scene.getStylesheets().add(getClass().getResource("/style/main.css").toExternalForm());

        stage.setTitle(properties.getName());
        stage.setResizable(properties.isResizeable());
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/weather-72x72.png")));

        stage.setScene(scene);
        stage.show();

        LOGGER.info("{} {} started", properties.getName(), properties.getVersion());
    }

}
