module com.example.graphproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graphproject to javafx.fxml;
    exports com.example.graphproject;
}