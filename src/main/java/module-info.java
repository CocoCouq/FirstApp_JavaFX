module org.firstapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.firstapp.controllers to javafx.fxml;
    opens org.firstapp.POJO to javafx.base;
    exports org.firstapp;
}