package org.firstapp.controllers.JDBC;

import javafx.fxml.Initializable;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Menu for second part of exercises : JDBC
 *
 * @see MenuController
 * @see SelectFouController
 * @see SelectOrdersController
 * @see NewSupplierController
 */
public class MenuController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Back to home
     * @see MenuController
     * @throws IOException : Exception
     */
    public void btn_return() throws IOException {
        App.setRoot("views/menu");
    }

    /**
     * Exercise 1 : Select supplier by primary key
     * @see SelectFouController
     * @throws IOException : Exception
     */
    public void btn1() throws IOException {
        App.setRoot("views/JDBC/select_fou");
    }

    /**
     * Exercise 2 : Select all orders for one supplier with a ComboBox
     * @see SelectOrdersController
     * @throws IOException : Exception
     */
    public void btn2() throws IOException {
        App.setRoot("views/JDBC/select_order");
    }

    /**
     * Exercise 3 : Add new supplier on data base
     * @see NewSupplierController
     * @throws IOException : Exception
     */
    public void btn3() throws IOException {
        App.setRoot("views/JDBC/new_fou");
    }
}
