module org.firstapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.firstapp.controllers to javafx.fxml;
    exports org.firstapp;
}