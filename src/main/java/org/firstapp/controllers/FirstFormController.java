package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstFormController implements Initializable {
    public javafx.scene.layout.AnchorPane AnchorPane;
    public Button button1;
    public Label label1;
    public TextField textField1;
    public Button button2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void button1_click(ActionEvent actionEvent) {
        String str = this.textField1.getText();
        this.label1.setText(str);
    }

    public void button2_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/menu");
    }
}
