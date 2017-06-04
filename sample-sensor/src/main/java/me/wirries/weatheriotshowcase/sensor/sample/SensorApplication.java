package me.wirries.weatheriotshowcase.sensor.sample;

import com.guigarage.flatterfx.FlatterFX;
import com.guigarage.flatterfx.FlatterInputType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * This is the main class of the sensor.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
public class SensorApplication extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(SensorApplication.class);

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
        final Scene scene = new Scene(root, 640, 480);

        stage.setTitle("Sensor");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/weather-72x72.png")));

        stage.setScene(scene);

        stage.show();
        FlatterFX.style(FlatterInputType.DEFAULT);
    }

    /**
     * Start the application.
     *
     * @param args Arguments from the command line
     */
    public static void main(final String[] args) {
        LOG.info("Starting application with {}", Arrays.toString(args));

        Application.launch(args);
    }

}