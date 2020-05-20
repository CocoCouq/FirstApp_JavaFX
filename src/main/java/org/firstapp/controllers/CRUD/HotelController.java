package org.firstapp.controllers.CRUD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Hotel CRUD View Controller
 *
 * @see ClientDAO
 * @see ClientHotel
 */
public class HotelController implements Initializable {
    // FXML Variables
    @FXML
    private TitledPane upAccordion;
    @FXML
    private TableColumn<ClientHotel, String> colName, colFirstName;
    @FXML
    private TextField addName, addAddress, addFirstName, addCity, upName, upFirstName, upAddress, upCity, upId;
    @FXML
    private TableView<ClientHotel> tableClients;
    // Class variables
    private final ClientDAO clientDAO = new ClientDAO();
    private final ObservableList<ClientHotel> list = FXCollections.observableArrayList();
    private boolean isValid = true;
    private String messageError = "";
    // Regex Filters
    private final String filterWord = "^[a-zA-Zéèêëàáâîïç\\-]+$";
    private final String filterAddress = "^((\\d)+[a-zA-Zéèêëàáâîïç\\-\\s.']{3,40})$";
    private final String filterCity = "^[a-zA-Zéèêëàáâîïç\\-\\s]+$";

    /**
     * Controller initialization
     * Add event listener on table click
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defineTable();

        tableClients.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.upAccordion.expandedProperty().set(true);
                this.upName.setText(newSelection.getName());
                this.upFirstName.setText(newSelection.getFirstName());
                this.upAddress.setText(newSelection.getAddress());
                this.upCity.setText(newSelection.getCity());
                this.upId.setText(String.valueOf(newSelection.getId()));
            }
        });
    }

    /**
     * Function to show list of client
     */
    public void defineTable() {
        for (int i = 0; i < this.tableClients.getItems().size(); i++) {
            this.tableClients.getItems().clear();
        }
        List<ClientHotel> dbList = clientDAO.list();
        this.list.addAll(dbList);

        tableClients.setEditable(true);

        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colFirstName.setCellValueFactory((new PropertyValueFactory<>("firstName")));

        tableClients.setItems(this.list);
    }

    /**
     * Back to JDBC Menu
     * @throws IOException : Exception
     */
    public void btn_return() throws IOException {
        App.setRoot("views/JDBC/menu_jdbc");
    }

    /**
     * Clear inputs values
     * @param actionEvent : Event click on reset button
     */
    public void btnReset(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        String action = (String) node.getUserData();

        if (action.equals("up")) {
            this.upName.setText("");
            this.upFirstName.setText("");
            this.upAddress.setText("");
            this.upCity.setText("");
        }
        else if (action.equals("add")) {
            this.addName.setText("");
            this.addFirstName.setText("");
            this.addAddress.setText("");
            this.addCity.setText("");
        }
    }

    /**
     * Function to insert new client on table view
     * @throws SQLException : Exception
     */
    public void btnAdd() throws SQLException {
        this.isValid = true;

        ClientHotel client = new ClientHotel();
        client.setName(this.addName.getText());
        client.setFirstName(this.addFirstName.getText());
        client.setCity(this.addCity.getText());
        client.setAddress(this.addAddress.getText());

        filterValue(client.getName(), filterWord, "Le nom n'est pas valide");
        filterValue(client.getFirstName(), filterWord, "Le prénom n'est pas valide");
        filterValue(client.getAddress(), filterAddress, "L'adresse n'est pas valide");
        filterValue(client.getCity(), filterCity, "La ville n'est pas valide");

        if (this.isValid) {
            clientDAO.insert(client);
            defineTable();
            alert("Client ajoutés avec succès", "Succès", Alert.AlertType.INFORMATION);
        }
        else {
            alert(this.messageError, "Ajout rejeté", Alert.AlertType.ERROR);
        }
    }

    /**
     * Function to update clients values
     * @throws SQLException : Exception
     */
    public void btnUpdate() throws SQLException {
        this.isValid = true;

        ClientHotel client = new ClientHotel();
        client.setName(this.upName.getText());
        client.setFirstName(this.upFirstName.getText());
        client.setAddress(this.upAddress.getText());
        client.setCity(this.upCity.getText());
        client.setId(Integer.parseInt(this.upId.getText()));

        filterValue(client.getName(), filterWord, "Le nom n'est pas valide");
        filterValue(client.getFirstName(), filterWord, "Le prénom n'est pas valide");
        filterValue(client.getAddress(), filterAddress, "L'adresse n'est pas valide");
        filterValue(client.getCity(), filterCity, "La ville n'est pas valide");

        if (this.isValid) {
            clientDAO.update(client);
            defineTable();
            alert("Modification ajoutées avec succès", "Succès", Alert.AlertType.INFORMATION);
        }
        else {
            alert(this.messageError, "Modifications rejetées", Alert.AlertType.ERROR);
        }
    }

    /**
     * Function to test input with regex
     * @param input : input to test
     * @param filter : regex filter
     * @param message : message error
     */
    public void filterValue(String input, String filter, String message) {
        if (!input.matches(filter)) {
            this.isValid = false;
            this.messageError = message;
        }
    }

    /**
     * Function to delete one client and reservations associated
     * @throws SQLException : Exception
     */
    public void btnDelete() throws SQLException {
        ClientHotel client = new ClientHotel();
        client.setId(Integer.parseInt(this.upId.getText()));

        clientDAO.delete(client);
        defineTable();

        this.upName.setText("");
        this.upFirstName.setText("");
        this.upAddress.setText("");
        this.upCity.setText("");
        this.upId.setText("");
    }

    /**
     * Function to display alert box
     * @param message : Message to display
     * @param header : Header alert
     * @param alertType : Alert Type
     */
    private void alert(String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Gestion des clients");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
