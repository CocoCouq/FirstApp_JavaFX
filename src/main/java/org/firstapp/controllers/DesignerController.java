package org.firstapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DesignerController implements Initializable {
    // Variables FXML
    @FXML
    private ToggleGroup caseText;
    @FXML
    private TitledPane caseArea;
    @FXML
    private CheckBox checkCase;
    @FXML
    private ToggleGroup text;
    @FXML
    private CheckBox checkTextColor;
    @FXML
    private TitledPane textArea;
    @FXML
    private ToggleGroup background;
    @FXML
    private Label labelText;
    @FXML
    private CheckBox checkBack;
    @FXML
    private TitledPane backgroundArea;
    @FXML
    private TitledPane choicesArea;
    @FXML
    private TextField inputText;

    // Initialization
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

    // Toggle visible areas
    public void disableChoices(KeyEvent keyEvent) {
        String string = this.inputText.getText();
        this.choicesArea.disableProperty().set(string.length() <= 0);
    }

    // Checkbox reset where uncheck
    public void resetCheckbox(TitledPane pane, CheckBox checkbox, ToggleGroup toggleGroup, String defaultStyle) {
        pane.disableProperty().set(!checkbox.isSelected());
        this.labelText.setStyle(this.labelText.getStyle() + ";" + defaultStyle);

        if (toggleGroup.getSelectedToggle() != null)
            toggleGroup.getSelectedToggle().setSelected(false);
    }

    // Background & Text Checkbox
    public void checkBackground(ActionEvent actionEvent) {
        resetCheckbox(this.backgroundArea, this.checkBack, this.background, "-fx-background-color: white");
    }

    public void checkText(ActionEvent actionEvent) {
        resetCheckbox(this.textArea, this.checkTextColor, this.text, "-fx-text-fill: black");
    }

    // Case checkbox
    public void checkCaseDisable(ActionEvent actionEvent) {
       this.caseArea.disableProperty().set(!this.checkCase.isSelected());
        this.labelText.setText(this.inputText.getText());

        if (this.caseText.getSelectedToggle() != null)
            this.caseText.getSelectedToggle().setSelected(false);
    }

    // Check what case user want
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

    // Change color by style property
    public void setStyleColor(ToggleGroup toggleGroup, String styleProperty) {
        if (toggleGroup.getSelectedToggle() != null) {
            String color = (String) toggleGroup.getSelectedToggle().getUserData();
            this.labelText.setStyle(this.labelText.getStyle() + ";" + styleProperty + ":" + color);
        }
    }

    // Set style and recover case for string
    public void setStyleCheckbox(ActionEvent actionEvent) {
        setStyleColor(this.background, "-fx-background-color");
        setStyleColor(this.text, "-fx-text-fill");
        // Case
        getStringResult();
    }
}
