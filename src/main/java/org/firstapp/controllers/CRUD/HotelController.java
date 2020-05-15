package org.firstapp.controllers.CRUD;

import com.google.protobuf.StringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    @FXML
    private TitledPane upAccordion;
    @FXML
    private TableColumn<ClientHotel, String> colName, colFirstName;
    @FXML
    private TextField addName, addAddress, addFirstName, addCity, upName, upFirstName, upAddress, upCity, upId;
    @FXML
    private TableView<ClientHotel> tableClients;
    @FXML
    private ImageView background;
    @FXML
    private AnchorPane firstPane;

    private ClientDAO clientDAO = new ClientDAO();
    private ObservableList<ClientHotel> list = FXCollections.observableArrayList();
    private boolean isValid = true;
    private String messageError = "";

    private final String filterWord = "^[a-zA-Zéèêëàáâîïç\\-]+$";
    private final String filterAddress = "^((\\d)+[a-zA-Zéèêëàáâîïç\\-\\s.']{3,40})$";
    private final String filterCity = "^[a-zA-Zéèêëàáâîïç\\-\\s]+$";

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

    public void defineTable() {
        for (int i = 0; i < this.tableClients.getItems().size(); i++) {
            this.tableClients.getItems().clear();
        }
        try {
            List<ClientHotel> dbList = clientDAO.list();
            this.list.addAll(dbList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableClients.setEditable(false);

        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colFirstName.setCellValueFactory((new PropertyValueFactory<>("firstName")));

        tableClients.setItems(this.list);
    }

    public void btn_return(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/JDBC/menu_jdbc");
    }

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

    public void btnAdd(ActionEvent actionEvent) {

    }

    public void btnUpdate(ActionEvent actionEvent) throws SQLException {
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

    public void filterValue(String input, String filter, String message) {
        if (!input.matches(filter)) {
            this.isValid = false;
            this.messageError = message;
        }
    }

    private void alert(String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Gestion des clients");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
