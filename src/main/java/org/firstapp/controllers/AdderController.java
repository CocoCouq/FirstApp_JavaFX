package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdderController implements Initializable {

    // Variables
    @FXML
    private TextArea TextArea_calc;
    private long result;
    private boolean start;

    // Initialize controller
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.result = 0;
        this.start = false;
    }

    // Return button
    public void button_return_click() throws IOException {
        App.setRoot("views/menu");
    }

    // Reset text
    public void clearText() {
        this.result = 0;
        this.start = false;
        this.TextArea_calc.clear();
    }

    // Button calc
    public void add() {
        this.TextArea_calc.clear();
        this.TextArea_calc.appendText(String.valueOf(this.result));
    }

    // Click on numbers
    public void clickNumber(ActionEvent actionEvent) {
        // Recover UserData in fxml
        Node node = (Node) actionEvent.getSource();
        String value = (String) node.getUserData();
        int nbr = Integer.parseInt(value);

        this.result = this.result + nbr;

        if (this.start)
            this.TextArea_calc.appendText(" + " + value + " = " + this.result);
        else {
            this.TextArea_calc.appendText(value);
            this.start = true;
        }
    }
}
