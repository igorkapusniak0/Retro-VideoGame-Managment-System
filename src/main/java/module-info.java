module com.example.ca2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ca2 to javafx.fxml;
    exports com.example.ca2;
    exports models;
    opens models to javafx.fxml;
    exports Controllers;
    opens Controllers to javafx.fxml;
    opens utils to javafx.base;
}