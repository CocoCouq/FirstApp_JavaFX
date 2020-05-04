package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void button_form1_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/firstform");
    }

    public void button_adder_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/adder");
    }

    public void button_designer_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/designer");
    }

    public void button_mixer_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/mixer");
    }
}
