package com.example.graphproject.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import com.example.graphproject.model.*;

public class MainController {
    @FXML
    AnchorPane graphCanvas;

    @FXML
    protected void onAddVertexButtonClick() {
        graphCanvas.getChildren().add(new Circle(200, 200, 200));
    }
}