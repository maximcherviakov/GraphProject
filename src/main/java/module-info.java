module com.example.graphproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jgrapht.core;


    opens com.example.graphproject to javafx.fxml;
    exports com.example.graphproject;
    exports com.example.graphproject.controller;
    opens com.example.graphproject.controller to javafx.fxml;
}