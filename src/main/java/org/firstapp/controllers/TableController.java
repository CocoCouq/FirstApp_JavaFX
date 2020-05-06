package org.firstapp.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class TableController implements Initializable {
    // Textfield variables
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_first_name;
    @FXML
    private TextField text_city;
    // Table variables
    @FXML
    private TableView<ClientTable> clients_list;
    @FXML
    private TableColumn<ClientTable, String> col_name;
    @FXML
    private TableColumn<ClientTable, String> col_first_name;
    @FXML
    private TableColumn<ClientTable, String> col_city;
    // Observable list of new ClientTable
    public ObservableList<ClientTable> list = FXCollections.observableArrayList();

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
    public void button_return(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/menu");
    }

    // Save new user
    public void save(ActionEvent actionEvent) {
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

    // Clear all inputs
    public void reset(ActionEvent actionEvent) {
        this.text_city.clear();
        this.txt_first_name.clear();
        this.txt_name.clear();
    }

    // Delete one of list
    public void delete(ActionEvent actionEvent) {
        list.remove(this.clients_list.getSelectionModel().getFocusedIndex());
    }
}
