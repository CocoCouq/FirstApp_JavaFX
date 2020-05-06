package org.firstapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * <b>First application in Java FX to show my first exercises</b>
 *
 * <br>This class contain first view and function to change view
 * @version 1.1.0
 * @author Corentin Couq
 * @see org.firstapp.controllers.MenuController
 */
public class App extends Application {
    private static Scene scene;

    /**
     * On start show menu
     * @see org.firstapp.controllers.MenuController
     * @param stage : Stage for the primary stage
     * @throws IOException : Exception
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("views/menu"));
        stage.setScene(scene);
        stage.setTitle("Ma première App");
        stage.show();
    }

    /**
     * Set root use loadFXML function to add views on the scene
     * @param fxml : String to fxml files
     * @throws IOException : Exception
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Function to define and load fxml files
     * @param fxml : String to fxml file
     * @return FXMLLoader file
     * @throws IOException : Exception
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main to launch app
     * @param args : Main arguments
     */
    public static void main(String[] args) {
        launch();
    }

}