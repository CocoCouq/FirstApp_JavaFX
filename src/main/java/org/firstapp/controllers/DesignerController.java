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
        getInputText().textProperty().addListener( evt -> {
            getStringResult();
        });
    }

    // Back to menu
    public void return_menu() throws IOException {
        App.setRoot("views/menu");
    }

    // Toggle visible areas
    public void disableChoices(KeyEvent keyEvent) {
        String string = getInputText().getText();
        getChoicesArea().disableProperty().set(string.length() <= 0);
    }

    // Checkbox reset where uncheck
    public void resetCheckbox(TitledPane pane, CheckBox checkbox, ToggleGroup toggleGroup, String defaultStyle) {
        pane.disableProperty().set(!checkbox.isSelected());
        getLabelText().setStyle(getLabelText().getStyle() + ";" + defaultStyle);

        if (toggleGroup.getSelectedToggle() != null)
            toggleGroup.getSelectedToggle().setSelected(false);
    }

    // Background & Text Checkbox
    public void checkBackground(ActionEvent actionEvent) {
        resetCheckbox(getBackgroundArea(), getCheckBack(), getBackground(), "-fx-background-color: white");
    }

    public void checkText(ActionEvent actionEvent) {
        resetCheckbox(getTextArea(), getCheckTextColor(), getText(), "-fx-text-fill: black");
    }

    // Case checkbox
    public void checkCaseDisable(ActionEvent actionEvent) {
        getCaseArea().disableProperty().set(!getCheckCase().isSelected());
        getLabelText().setText(getInputText().getText());

        if (getCaseText().getSelectedToggle() != null)
            getCaseText().getSelectedToggle().setSelected(false);
    }

    // Check what case user want
    public void getStringResult() {
        if (getCaseText().getSelectedToggle() != null) {

            if ((getCaseText().getSelectedToggle().getUserData()).equals("up"))
                getLabelText().setText(getInputText().getText().toUpperCase());
            else if ((getCaseText().getSelectedToggle().getUserData()).equals("low"))
                getLabelText().setText(getInputText().getText().toLowerCase());
            else
                getLabelText().setText(getInputText().getText());

        }
        else
            getLabelText().setText(getInputText().getText());
    }

    // Change color by style property
    public void setStyleColor(ToggleGroup toggleGroup, String styleProperty) {
        if (toggleGroup.getSelectedToggle() != null) {
            String color = (String) toggleGroup.getSelectedToggle().getUserData();
            getLabelText().setStyle(getLabelText().getStyle() + ";" + styleProperty + ":" + color);
        }
    }

    // Set style and recover case for string
    public void setStyleCheckbox(ActionEvent actionEvent) {
        setStyleColor(getBackground(), "-fx-background-color");
        setStyleColor(getText(), "-fx-text-fill");
        // Case
        getStringResult();
    }

    // GETTERS & SETTERS
    public TextField getInputText() {
        return inputText;
    }

    public TitledPane getChoicesArea() {
        return choicesArea;
    }

    public TitledPane getBackgroundArea() {
        return backgroundArea;
    }

    public CheckBox getCheckBack() {
        return checkBack;
    }

    public Label getLabelText() {
        return labelText;
    }

    public ToggleGroup getBackground() {
        return background;
    }

    public TitledPane getTextArea() {
        return textArea;
    }

    public CheckBox getCheckTextColor() {
        return checkTextColor;
    }

    public ToggleGroup getText() {
        return text;
    }

    public TitledPane getCaseArea() {
        return caseArea;
    }

    public CheckBox getCheckCase() {
        return checkCase;
    }

    public ToggleGroup getCaseText() {
        return caseText;
    }
}
