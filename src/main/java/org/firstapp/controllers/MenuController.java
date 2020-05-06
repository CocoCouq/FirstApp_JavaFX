package org.firstapp.controllers;

import javafx.fxml.Initializable;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Home page controller : access to all pages
 *
 * @see FirstFormController
 * @see AdderController
 * @see DesignerController
 * @see ColorMixerController
 * @see TableController
 */
public class MenuController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * First Form
     * @see FirstFormController
     * @throws IOException : Exception
     */
    public void button_form1_click() throws IOException {
        App.setRoot("views/firstform");
    }

    /**
     * Adder
     * @see AdderController
     * @throws IOException : Exception
     */
    public void button_adder_click() throws IOException {
        App.setRoot("views/adder");
    }

    /**
     * String Designer
     * @see DesignerController
     * @throws IOException : Exception
     */
    public void button_designer_click() throws IOException {
        App.setRoot("views/designer");
    }

    /**
     * Color Mixer
     * @see ColorMixerController
     * @throws IOException : Exception
     */
    public void button_mixer_click() throws IOException {
        App.setRoot("views/colormixer");
    }

    /**
     * Table of clients
     * @see TableController
     * @throws IOException : Exception
     */
    public void button_table_click() throws IOException {
        App.setRoot("views/table");
    }
}
