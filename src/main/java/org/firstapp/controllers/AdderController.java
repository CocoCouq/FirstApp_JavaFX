package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdderController implements Initializable {
    public Button button_return;
    public TextArea TextArea_calc;
    public Button btn_4;
    public Button btn_clear;
    public Button btn_calc;
    public Button btn_2;
    public Button btn_1;
    public Button btn_3;
    public Button btn_5;
    public Button btn_6;
    public Button btn_7;
    public Button btn_8;
    public Button btn_9;
    public Button btn_0;
    public long result = 0;
    public boolean start = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void button_return_click(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/menu");
    }

    public void clickNumber(long nbr) {
        this.result += nbr;
        if (this.start) {
            this.TextArea_calc.appendText(" + " + nbr + " = " + result);
        }
        else {
            this.TextArea_calc.appendText(nbr + "");

            this.start = true;
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
        this.result = 0;
        this.start = false;
        this.TextArea_calc.clear();
    }

    public void add(ActionEvent actionEvent) {
        this.TextArea_calc.clear();
        this.TextArea_calc.appendText(""+this.result);
    }
}
