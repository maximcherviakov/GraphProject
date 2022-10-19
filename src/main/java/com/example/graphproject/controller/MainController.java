package com.example.graphproject.controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import com.example.graphproject.model.*;

import java.util.Random;

public class MainController {
    private final double RADIUS_FIR_CIRCLE = 20;
    private final Paint COLOR_FOR_CIRCLE_BACKGROUND = Color.web("#F1F1F1");
    private final Paint COLOR_FOR_CIRCLE_STROKE = Color.web("#3299D9");
    @FXML
    AnchorPane graphCanvas;

    @FXML
    protected void onAddVertexButtonClick() {
        graphCanvas.getChildren().add(addNode(Math.random() * graphCanvas.getHeight(), Math.random() * graphCanvas.getWidth()));
    }

    @FXML
    protected void clearGraph() {
        graphCanvas.getChildren().clear();
    }

    private Circle addNode(double x, double y) {
        Circle circle = new Circle(x, y, RADIUS_FIR_CIRCLE, COLOR_FOR_CIRCLE_BACKGROUND);
        circle.setStrokeWidth(4);
        circle.setStroke(COLOR_FOR_CIRCLE_STROKE);
        return circle;
    }
}