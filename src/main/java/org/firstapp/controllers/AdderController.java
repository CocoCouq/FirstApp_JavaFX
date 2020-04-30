package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdderController implements Initializable {

    @FXML
    private TextArea TextArea_calc;
    private long result;
    private boolean start;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.result = 0;
        this.start = false;
    }

    public void button_return_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/menu");
    }

    private void clickNumber(long nbr) {
        setResult(getResult() + nbr);
        if (isStart()) {
            getTextArea_calc().appendText(" + " + nbr + " = " + getResult());
        }
        else {
            getTextArea_calc().appendText(nbr + "");

            setStart(true);
        }
    }

    public void click_0(ActionEvent actionEvent) {
        clickNumber(0);
    }

    public void click_1(ActionEvent actionEvent) {
        clickNumber(1);
    }

    public void click_2(ActionEvent actionEvent) {
        clickNumber(2);
    }

    public void click_3(ActionEvent actionEvent) {
        clickNumber(3);
    }

    public void click_4(ActionEvent actionEvent) {
        clickNumber(4);
    }

    public void click_5(ActionEvent actionEvent) {
        clickNumber(5);
    }

    public void click_6(ActionEvent actionEvent) {
        clickNumber(6);
    }

    public void click_7(ActionEvent actionEvent) {
        clickNumber(7);
    }

    public void click_8(ActionEvent actionEvent) {
        clickNumber(8);
    }

    public void click_9(ActionEvent actionEvent) {
        clickNumber(9);
    }

    public void clearText(ActionEvent actionEvent) {
        setResult(0);
        setStart(false);
        getTextArea_calc().clear();
    }

    public void add(ActionEvent actionEvent) {
        getTextArea_calc().clear();
        getTextArea_calc().appendText(""+getResult());
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
