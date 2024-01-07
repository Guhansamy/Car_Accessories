module com.example.projectcar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mongo.java.driver;


    opens com.example.projectcar to javafx.fxml;
    exports com.example.projectcar;
}