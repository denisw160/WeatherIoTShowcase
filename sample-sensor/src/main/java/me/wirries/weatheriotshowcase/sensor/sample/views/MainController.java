package me.wirries.weatheriotshowcase.sensor.sample.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import me.wirries.weatheriotshowcase.sensor.sample.model.Interfaces;
import me.wirries.weatheriotshowcase.sensor.sample.model.Locations;
import me.wirries.weatheriotshowcase.sensor.sample.service.AliveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
    private ToggleButton automatic;
    @FXML
    public ComboBox<Interfaces> interfaces;
    @FXML
    private Label intervalValue;
    @FXML
    private Slider interval;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private boolean transferActive;

    private AliveService aliveService;

    void setAliveService(final AliveService aliveService) {
        this.aliveService = aliveService;
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
        locationChanged(null);

        // init temperature
        temperature.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue, final Number newValue) {
                temperature.setValue(round(newValue.doubleValue(), 1));
                updateValues();
            }
        });
        temperature.setValue(20.0);

        // init jobs
        activateAliveJob();

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

    private void updateValues() {
        final Double value = interval.getValue();
        intervalValue.setText(value.intValue() + "s");

        temperatureValue.setText(temperature.getValue() + "°C");
    }

    public void automaticChanged(final ActionEvent actionEvent) {
        transferActive = !transferActive;
        updateFieldState();
    }

    private void updateFieldState() {
        interfaces.setDisable(transferActive);
        interval.setDisable(transferActive);
        locations.setDisable(transferActive);
        lat.setDisable(transferActive);
        lon.setDisable(transferActive);
    }

    public void manualRefresh(final MouseEvent mouseEvent) {
        if (transferActive) return;
        System.out.println("Refresh");
    }

    public void locationChanged(final ActionEvent actionEvent) {
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

    public void interfaceChanged(final ActionEvent actionEvent) {
        // no actions yet
    }

    private double round(final Double value, final int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
