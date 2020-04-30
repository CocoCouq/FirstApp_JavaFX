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
        setResult(0);
        setStart(false);
        getTextArea_calc().clear();
    }

    // Button calc
    public void add() {
        getTextArea_calc().clear();
        getTextArea_calc().appendText(String.valueOf(getResult()));
    }

    // Click on numbers
    public void clickNumber(ActionEvent actionEvent) {
        // Recover UserData in fxml
        Node node = (Node) actionEvent.getSource();
        String value = (String) node.getUserData();
        int nbr = Integer.parseInt(value);

        setResult(getResult() + nbr);

        if (isStart())
            getTextArea_calc().appendText(" + " + value + " = " + getResult());
        else {
            getTextArea_calc().appendText(value);
            setStart(true);
        }

    }

    // GETTER & SETTER
    public long getResult() {
        return result;
    }

    public void setResult(long result) {
        this.result = result;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public TextArea getTextArea_calc() {
        return TextArea_calc;
    }

}
