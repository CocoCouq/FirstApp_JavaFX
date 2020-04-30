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

public class FirstFormController implements Initializable {
    @FXML
    private Label label1;
    @FXML
    private TextField textField1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void button1_click(ActionEvent actionEvent) {
        String str = getTextField1().getText();
        getLabel1().setText(str);
    }

    public void button2_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/menu");
    }

    // GETTERS & SETTERS

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(Label label1) {
        this.label1 = label1;
    }

    public TextField getTextField1() {
        return textField1;
    }

    public void setTextField1(TextField textField1) {
        this.textField1 = textField1;
    }
}
