package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColorMixerController implements Initializable {
    // FXML variables
    @FXML
    private Label labelHex;
    @FXML
    private Label labelRed;
    @FXML
    private Label labelGreen;
    @FXML
    private Label labelBlue;
    @FXML
    private Label labelTrans;
    @FXML
    private Rectangle transSqrt;
    @FXML
    private Slider sliderTrans;
    @FXML
    private Rectangle mixerBox;
    @FXML
    private Rectangle colorSqrt3;
    @FXML
    private Rectangle colorSqrt2;
    @FXML
    private Rectangle colorSqrt1;
    @FXML
    private Slider slider1;
    @FXML
    private Slider slider2;
    @FXML
    private Slider slider3;

    // Class variables
    private int valueS1 = 0;
    private int valueS2 = 0;
    private int valueS3 = 0;
    private float transparency = 1;

    // Event on views initialization
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // RED
        this.slider1.valueProperty().addListener( (evt, old_value, new_value) -> {
            this.valueS1 = defineRGB(new_value);

            this.colorSqrt1.setFill(Color.rgb(this.valueS1, 0, 0));
            displayColor(this.labelRed, this.valueS1);
        });
        // GREEN
        this.slider2.valueProperty().addListener( (evt, old_value, new_value) -> {
            this.valueS2 = defineRGB(new_value);

            this.colorSqrt2.setFill(Color.rgb(0, this.valueS2, 0));
            displayColor(this.labelGreen, this.valueS2);
        });
        // BLUE
        this.slider3.valueProperty().addListener( (evt, old_value, new_value) -> {
            this.valueS3 = defineRGB(new_value);

            this.colorSqrt3.setFill(Color.rgb(0, 0, this.valueS3));
            displayColor(this.labelBlue, this.valueS3);
        });

        this.sliderTrans.valueProperty().addListener( (evt, old_value, new_value) -> {
            this.transparency = new_value.intValue();
            this.transparency = 1 - (this.transparency / 100);

            this.transSqrt.setFill(Color.rgb(255, 255, 255, this.transparency));
            displayColor(this.labelTrans, this.transparency);
        });
    }

    // CLASS FUNCTIONS
    // Define slider result for 255
    public int defineRGB(Number number) {
        return (number.intValue() * 255) / 100;
    }

    // Define the string to hex value of color
    public String hexValue() {
        return "#" + Integer.toHexString(valueS1) + Integer.toHexString(valueS2) + Integer.toHexString(valueS3);
    }

    // Display color after event on a slider & Display result for RGB
    public void displayColor(Label label, Number value) {
        this.mixerBox.setFill(Color.rgb(this.valueS1, this.valueS2, this.valueS3, this.transparency));
        label.setText(String.valueOf(value));
        this.labelHex.setText(hexValue());
    }

    // Go back to menu
    public void button_return(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/menu");
    }

}
