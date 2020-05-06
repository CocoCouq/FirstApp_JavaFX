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

/**
 * Exercise 3 : Color Mixer
 * @author Corentin Couq
 */
public class ColorMixerController implements Initializable {
    // FXML variables
    @FXML
    private Label labelHex, labelRed, labelGreen, labelBlue, labelTrans;
    @FXML
    private Rectangle transSqrt, mixerBox, colorSqrt3, colorSqrt2, colorSqrt1;
    @FXML
    private Slider sliderTrans, slider1, slider2, slider3;

    // Class variables
    private int valueS1 = 0;
    private int valueS2 = 0;
    private int valueS3 = 0;
    private float transparency = 1;

    /**
     * Controller initialization : add event listener on the 4 labels (Red, Green, Blue, Transparency)
     */
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
    /**
     * Function to define slider result for 255
     * @param number : number of slider
     * @return Value / 255
     */
    public int defineRGB(Number number) {
        return (number.intValue() * 255) / 100;
    }

    /**
     * Function to define hex value for color
     * @return Hex string color value
     */
    public String hexValue() {
        return "#" + Integer.toHexString(valueS1) + Integer.toHexString(valueS2) + Integer.toHexString(valueS3);
    }

    /**
     * Function tu display colors and value result
     * @param label : label to set value
     * @param value : color value
     */
    public void displayColor(Label label, Number value) {
        this.mixerBox.setFill(Color.rgb(this.valueS1, this.valueS2, this.valueS3, this.transparency));
        label.setText(String.valueOf(value));
        this.labelHex.setText(hexValue());
    }

    public void button_return() throws IOException {
        App.setRoot("views/menu");
    }

}
