package org.firstapp.controllers.JDBC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.firstapp.App;
import org.firstapp.POJO.Supplier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Add new supplier on database
 *
 * @see Supplier
 */
public class NewSupplierController implements Initializable {
    // FXML Variables
    @FXML
    private TextField nameInput, streetInput, cityInput, zipInput, contactInput;
    // Class variables
    private boolean isValid;
    private String messageError = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Back to jdbc menu
     * @throws IOException : Exception
     */
    public void btn_return() throws IOException {
        App.setRoot("views/JDBC/menu_jdbc");
    }

    /**
     * Function to add supplier
     */
    public void addSupplier() {
        // Define values
        String name = this.nameInput.getText();
        String street = this.streetInput.getText();
        String zip = this.zipInput.getText();
        String city = this.cityInput.getText();
        String contact = this.contactInput.getText();

        // Define patterns
        String filterWord = "^[a-zA-Zéèêëàáâîïç\\-]+$";
        String filterString = "^((\\d)+[a-zA-Zéèêëàáâîïç\\-\\s.]{3,40})$";
        String filterZip = "^[0-9]{5}$";
        // Regex tests
        this.isValid = true;
        this.filterValue(contact, filterWord, "Le nom du contact n'est pas valide");
        this.filterValue(zip, filterZip, "Le code postal doit comporter 5 chiffres");
        this.filterValue(city, filterWord, "La ville n'est pas valide");
        this.filterValue(street, filterString, "L'adresse n'est pas valide");
        this.filterValue(name, filterWord, "Le nom de l'entreprise n'est pas valide");

        // Define new Supplier
        Supplier supplier = new Supplier(name, street, zip, city, contact);
        // addSupplier return boolean if supplier is add
        if(this.isValid) {
            if (supplier.addSupplier()) {
                resetValues();
                this.alert("Ajouté avec succès", "Succès", Alert.AlertType.INFORMATION);
            }
        }
        else
            this.alert(this.messageError, "Erreur", Alert.AlertType.ERROR);
    }

    /**
     * Clear all inputs values
     */
    public void resetValues() {
        this.cityInput.setText("");
        this.nameInput.setText("");
        this.streetInput.setText("");
        this.contactInput.setText("");
        this.zipInput.setText("");
    }

    /**
     * Test input with regex and define an error message
     * @param input : Input string value
     * @param filter : Regex
     * @param message : Message if error
     */
    public void filterValue(String input, String filter, String message) {
        if (!input.matches(filter)) {
            this.isValid = false;
            this.messageError = message;
        }
    }

    /**
     * Create an alert for success and errors
     * @param message : Alert content
     * @param header : Alert Header
     * @param alertType : Type of alert
     */
    private void alert(String message, String header, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Nouveau fournisseur");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
