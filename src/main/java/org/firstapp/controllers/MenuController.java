package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {


    public AnchorPane MenuPane;
    public Button button_form1;
    public Button button_adder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void button_form1_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/firstform");
    }

    public void button_adder_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/adder");
    }
}
