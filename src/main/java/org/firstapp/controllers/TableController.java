package org.firstapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.firstapp.App;
import org.firstapp.POJO.ClientTable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Exercise 4 : Table of clients
 * @author Corentin Couq
 */
public class TableController implements Initializable {
    // Variables
    @FXML
    private TextField txt_name, txt_first_name, text_city;
    @FXML
    private TableView<ClientTable> clients_list;
    @FXML
    private TableColumn<ClientTable, String> col_name, col_first_name, col_city;
    // Observable list of new ClientTable
    public ObservableList<ClientTable> list = FXCollections.observableArrayList();

    /**
     * Controller initialization : Insert 4 clients on table
     * @see ClientTable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // List insertion on initialization
        list.add(new ClientTable("Josh", "Homme", "Joshua Tree"));
        list.add(new ClientTable("Dave", "Grohl", "Warren"));
        list.add(new ClientTable("Krist", "Novoselic", "Compton"));
        list.add(new ClientTable("Robert", "Trujillo", "Santa Monica"));

        // Can't edit table
        clients_list.setEditable(false);

        // Join table content
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));

        // Choose list template
        clients_list.setItems(list);
    }

    // Back to menu
    public void button_return() throws IOException {
        App.setRoot("views/menu");
    }

    /**
     * Function to save client on click
     */
    public void save() {
        ClientTable newClient = new ClientTable();
        // Set new values
        try {
            newClient.setName(this.txt_name.getText());
            newClient.setFirstName(this.txt_first_name.getText());
            newClient.setCity(this.text_city.getText());

            list.add(newClient);
        }
        catch (Exception e) {
            // else print error message
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function to delete Input text value
     */
    public void reset() {
        this.text_city.clear();
        this.txt_first_name.clear();
        this.txt_name.clear();
    }

    // Delete one of list

    /**
     * Delete one client selected
     */
    public void delete() {
        list.remove(this.clients_list.getSelectionModel().getFocusedIndex());
    }
}
