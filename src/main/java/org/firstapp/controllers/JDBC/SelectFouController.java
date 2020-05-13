package org.firstapp.controllers.JDBC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.firstapp.App;
import org.firstapp.POJO.Supplier;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Select all details for one supplier
 *
 * @see Supplier
 */
public class SelectFouController implements Initializable {
    // FXML variables
    @FXML
    private TextField nameInput,contactInput, cityInput, zipInput, codeInput;
    @FXML
    private TextArea addressInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Back to JDBC exercises menu
     * @throws IOException : Exception
     */
    public void btn_return() throws IOException {
        App.setRoot("views/JDBC/menu_jdbc");
    }

    /**
     * On click on search button
     * @throws SQLException : Sql Exception
     */
    public void btnSearch() throws SQLException {
        // Clear inputs values
        this.nameInput.setText("");
        this.contactInput.setText("");
        this.addressInput.setText("");
        this.cityInput.setText("");
        this.zipInput.setText("");


        try {
            ResultSet result = new Supplier().supplierByNum(this.codeInput.getText());

            if (result.next()) {
                    this.nameInput.setText(result.getString("nomfou"));
                    this.contactInput.setText(result.getString("confou"));
                    this.addressInput.setText(result.getString("ruefou"));
                    this.cityInput.setText(result.getString("vilfou"));
                    this.zipInput.setText(result.getString("posfou"));
            }
            else {
                this.codeInput.setText("INCONNU");
            }

            result.close();

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
            this.codeInput.setText("INCONNU");
        }
    }
}
