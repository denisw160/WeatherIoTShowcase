package me.wirries.weatheriotshowcase.sensor.sample.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;

/**
 * This controller handles the main view.
 *
 * @author Denis Wirries
 * @version 1.0
 * @since 04.06.2017
 */
@Component
public class MainController {

    @FXML
    private ComboBox locations;
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
    public ComboBox interfaces;
    @FXML
    private Label updateValue;
    @FXML
    private Slider interval;

    @FXML
    public void initialize() {
        System.out.println("Init done");
    }

    public void manualRefresh(MouseEvent mouseEvent) {

    }

    public void locationChanged(ActionEvent actionEvent) {

    }

    public void automaticChanged(ActionEvent actionEvent) {

    }

    public void interfaceChanged(ActionEvent actionEvent) {

    }
}
