package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * First Form created : The real first exercise Java FX
 */
public class FirstFormController implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private TextField textField1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * To set input text value on label value
     */
    public void button1_click() {
        String str = this.textField1.getText();
        this.label1.setText(str);
    }

    public void button2_click() throws IOException {
        App.setRoot("views/menu");
    }
}
