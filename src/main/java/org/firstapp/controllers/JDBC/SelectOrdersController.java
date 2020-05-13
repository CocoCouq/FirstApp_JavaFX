package org.firstapp.controllers.JDBC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import org.firstapp.App;
import org.firstapp.POJO.Supplier;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Select all orders by supplier
 *
 * @see Supplier
 */
public class SelectOrdersController implements Initializable {
    // FXML Variables
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextArea ordersDetails;
    // Connection class
    private java.sql.Connection connection;

    /**
     * On start : Fetch all supplier and add to ComboBox
     * @param url : Pointer to a "resource" on the World Wide Web
     * @param resourceBundle : Locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ResultSet result = new Supplier().supplierList();
            // Add suppliers in ObservableList
            ObservableList<String> list = FXCollections.observableArrayList();
            while (result.next()) {
                list.add(result.getString("nomfou"));
            }
            // Add in ComboBox
            this.comboBox.getItems().setAll(list);

            result.close();
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

    /**
     * Back to JDBC menu
     * @throws IOException : Exception
     */
    public void btn_return() throws IOException {
        App.setRoot("views/JDBC/menu_jdbc");
    }

    /**
     * Select orders details where user click on
     * @throws SQLException : Sql Exception
     */
    public void boxAction() throws SQLException {
        String supplier = this.comboBox.getSelectionModel().getSelectedItem();

        ResultSet resultSet = new Supplier().ordersDetails(supplier);

        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            stringBuilder.append(resultSet.getInt("numcom")).append(" ")
                    .append(resultSet.getDate("datcom")).append(" ")
                    .append(resultSet.getString("obscom")).append("\n");
            this.ordersDetails.setText(String.valueOf(stringBuilder));
        }
        resultSet.close();
    }
}
