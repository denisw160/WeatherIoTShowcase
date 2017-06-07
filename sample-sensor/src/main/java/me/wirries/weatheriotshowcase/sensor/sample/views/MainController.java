package me.wirries.weatheriotshowcase.sensor.sample.views;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.wirries.weatheriotshowcase.sensor.sample.model.Interfaces;
import me.wirries.weatheriotshowcase.sensor.sample.model.Locations;
import me.wirries.weatheriotshowcase.sensor.sample.service.AliveService;
import me.wirries.weatheriotshowcase.sensor.sample.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * This controller handles the main view.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
@Component
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @FXML
    private ComboBox<Locations> locations;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;

    @FXML
    private Label temperatureValue;
    @FXML
    private Slider temperature;

    @FXML
    private ImageView connection;

    @FXML
    public ComboBox<Interfaces> interfaces;
    @FXML
    private Label intervalValue;
    @FXML
    private Slider interval;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private Runnable sendTask;
    private ScheduledFuture<?> sendFuture;

    private boolean transferActive;

    private AliveService aliveService;

    void setAliveService(final AliveService aliveService) {
        this.aliveService = aliveService;
    }

    private Map<String, WeatherService> weatherServices;

    void setWeatherServices(final Map<String, WeatherService> weatherServices) {
        this.weatherServices = weatherServices;
    }

    @FXML
    public void initialize() {
        // init data transfer
        transferActive = false;

        interfaces.setItems(FXCollections.observableArrayList(Interfaces.list()));
        interfaces.getSelectionModel().select(0);

        interval.valueProperty().addListener((observable, oldValue, newValue) -> {
            interval.setValue(round(newValue.doubleValue(), 0));
            updateValues();
        });
        interval.setValue(2.0);

        // init locations
        locations.setItems(FXCollections.observableArrayList(Locations.list()));
        locations.getSelectionModel().select(0);
        locationChanged();

        // init temperature
        temperature.valueProperty().addListener((observable, oldValue, newValue) -> {
            temperature.setValue(round(newValue.doubleValue(), 1));
            updateValues();
        });
        temperature.setValue(20.0);

        // init jobs
        activateAliveJob();
        createSendJob();

        LOGGER.debug("User Interface created");
    }

    private void activateAliveJob() {
        final Runnable aliveTask = () -> {
            final boolean reachable = aliveService.isReachable();
            LOGGER.debug("Host is reachable: {}", reachable);
            if (reachable) {
                connection.setImage(new Image("/images/green-32x32.png"));
            } else {
                connection.setImage(new Image("/images/red-32x32.png"));
            }
        };

        scheduler.scheduleAtFixedRate(aliveTask, 1, 30, SECONDS);
    }

    private void createSendJob() {
        sendTask = () -> {
            LOGGER.debug("Sending data");
            send();
        };
    }

    private void updateValues() {
        final Double value = interval.getValue();
        intervalValue.setText(value.intValue() + "s");

        temperatureValue.setText(temperature.getValue() + "°C");
    }

    public void automaticChanged() {
        transferActive = !transferActive;
        updateFieldState();

        if (transferActive) {
            final int period = ((Double) interval.getValue()).intValue();
            sendFuture = scheduler.scheduleAtFixedRate(sendTask, 0, period, SECONDS);
        } else if (sendFuture != null) {
            sendFuture.cancel(false);
        }
    }

    private void updateFieldState() {
        interfaces.setDisable(transferActive);
        interval.setDisable(transferActive);
        locations.setDisable(transferActive);
        lat.setDisable(transferActive);
        lon.setDisable(transferActive);
    }

    public void manualRefresh() {
        if (transferActive) return;

        send();
    }

    private void send() {
        final WeatherService service = getService(interfaces.getSelectionModel().getSelectedItem().name());

        final double lat = Double.parseDouble(this.lat.getText());
        final double lon = Double.parseDouble(this.lon.getText());

        final double temp = temperature.getValue();

        service.send(lat, lon, temp);
    }

    private WeatherService getService(final String type) {
        return weatherServices.get(type);
    }

    public void locationChanged() {
        final Locations item = locations.getSelectionModel().getSelectedItem();
        lat.setText(String.valueOf(item.getLat()));
        lon.setText(String.valueOf(item.getLon()));

        if (item == Locations.CUSTOM) {
            lat.setDisable(false);
            lon.setDisable(false);
        } else {
            lat.setDisable(true);
            lon.setDisable(true);
        }
    }

    public void interfaceChanged() {
        // no actions yet
    }

    private double round(final Double value, final int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
