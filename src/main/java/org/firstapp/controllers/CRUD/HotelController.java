package org.firstapp.controllers.CRUD;

import com.google.protobuf.StringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.firstapp.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HotelController implements Initializable {
    @FXML
    private ImageView background;
    @FXML
    private AnchorPane firstPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btn_return(ActionEvent actionEvent) throws IOException {
        App.setRoot("views/JDBC/menu_jdbc");
    }
}
