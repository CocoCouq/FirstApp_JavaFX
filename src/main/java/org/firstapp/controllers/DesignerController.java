package org.firstapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Exercise 2 : CSS Form
 * @author Corentin Couq
 */
public class DesignerController implements Initializable {
    // Variables FXML
    @FXML
    private ToggleGroup caseText, text, background;
    @FXML
    private TitledPane caseArea, textArea, backgroundArea, choicesArea;
    @FXML
    private CheckBox checkCase, checkTextColor, checkBack;
    @FXML
    private Label labelText;
    @FXML
    private TextField inputText;

    /**
     * Controller initialization : add event listener on to input value to label text
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inputText.textProperty().addListener( evt -> {
            getStringResult();
        });
    }

    // Back to menu
    public void return_menu() throws IOException {
        App.setRoot("views/menu");
    }

    /**
     * Enable choices if input is not empty on key press
     */
    public void disableChoices() {
        String string = this.inputText.getText();
        this.choicesArea.disableProperty().set(string.length() <= 0);
    }

    /**
     * Function to reset value where checkbox unchecked
     * @param pane : Titled Pane to disable
     * @param checkbox : Checkbox clicked
     * @param toggleGroup : Radios group to reset
     * @param defaultStyle : Restore default css value
     */
    public void resetCheckbox(TitledPane pane, CheckBox checkbox, ToggleGroup toggleGroup, String defaultStyle) {
        pane.disableProperty().set(!checkbox.isSelected());
        this.labelText.setStyle(this.labelText.getStyle() + ";" + defaultStyle);

        if (toggleGroup.getSelectedToggle() != null)
            toggleGroup.getSelectedToggle().setSelected(false);
    }

    /**
     * Click on background checkbox
     */
    public void checkBackground() {
        resetCheckbox(this.backgroundArea, this.checkBack, this.background, "-fx-background-color: white");
    }

    /**
     * Click on text checkbox
     */
    public void checkText() {
        resetCheckbox(this.textArea, this.checkTextColor, this.text, "-fx-text-fill: black");
    }

    /**
     * Click on case checkbox
     */
    public void checkCaseDisable() {
       this.caseArea.disableProperty().set(!this.checkCase.isSelected());
        this.labelText.setText(this.inputText.getText());

        if (this.caseText.getSelectedToggle() != null)
            this.caseText.getSelectedToggle().setSelected(false);
    }

    /**
     * Function to define case result
     */
    public void getStringResult() {
        if (this.caseText.getSelectedToggle() != null) {

            if ((this.caseText.getSelectedToggle().getUserData()).equals("up"))
                this.labelText.setText(this.inputText.getText().toUpperCase());
            else if ((this.caseText.getSelectedToggle().getUserData()).equals("low"))
                this.labelText.setText(this.inputText.getText().toLowerCase());
            else
                this.labelText.setText(this.inputText.getText());

        }
        else
            this.labelText.setText(this.inputText.getText());
    }

    /**
     * Set style to String (Background and text color)
     * @param toggleGroup : Radios group enable
     * @param styleProperty : CSS property to set
     */
    public void setStyleColor(ToggleGroup toggleGroup, String styleProperty) {
        if (toggleGroup.getSelectedToggle() != null) {
            String color = (String) toggleGroup.getSelectedToggle().getUserData();
            this.labelText.setStyle(this.labelText.getStyle() + ";" + styleProperty + ":" + color);
        }
    }

    /**
     * Set style and recover case
     */
    public void setStyleCheckbox() {
        setStyleColor(this.background, "-fx-background-color");
        setStyleColor(this.text, "-fx-text-fill");
        // Case
        getStringResult();
    }
}
