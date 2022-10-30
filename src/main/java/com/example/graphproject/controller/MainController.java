package com.example.graphproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import com.example.graphproject.model.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import org.jgrapht.graph.DefaultEdge;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Клас MainController.
 * Клас контролеру, який реалізує логіку поведінки елементів вікна
 */
public class MainController implements Initializable {
    private final double RADIUS_FOR_CIRCLE = 20;
    private final double CIRCLE_STROKE_WIDTH = 4;
    private final double LINE_STROKE_WIDTH = 3;
    private final double FONT_SIZE = 14;
    private final String FONT = "Roboto Light";
    private final Paint COLOR_FOR_CIRCLE_BACKGROUND = Color.web("#F1F1F1");
    private final Paint COLOR_FOR_CIRCLE_STROKE = Color.web("#3299D9");

    private final GraphInstance graphInstance = GraphInstance.getInstance();

    // Поля, які зберігають в собі елементи вікна,
    // що генеруються з файлу розмітки
    @FXML
    AnchorPane graphCanvas;
    @FXML
    TextField addVertexValueField;
    @FXML
    TextField removeVertexValueField;
    @FXML
    TextField addEdgeSourceValueField;
    @FXML
    TextField removeEdgeSourceValueField;
    @FXML
    TextField addEdgeTargetValueField;
    @FXML
    TextField removeEdgeTargetValueField;

    /**
     * Метод ініціалізації, тобто метод який виконується першим в контролері.
     * Даний метод потрібен для оновлення змісту вікна
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGraphCanvas();
    }

    /**
     * Обробник події натискання на кнопку "Add vertex".
     * Коли користувач натискає на дану кнопку, зчитується значення нової вершини
     * з текстового поля вище та додається до графа.
     * Якщо така вершина існує, то відкриється повідомлення про це.
     */
    @FXML
    protected void onAddVertexButtonClick() {
        try {
            int value = Integer.parseInt(addVertexValueField.getText());
            graphInstance.addVertex(value);
            addVertexValueField.clear();
            updateGraphCanvas();
        } catch(NumberFormatException numberFormatException) {
            showErrorWindowForIncorrectValues();
        } catch (IllegalArgumentException illegalArgumentException) {
            showErrorWindow(illegalArgumentException.getMessage());
        }
    }

    /**
     * Обробник події натискання на кнопку "Add edge"
     * Коли користувач натискає на дану кнопку, зчитується початок та кінець ребра
     * з текстового поля вище та додається до графа.
     * Якщо таке ребро існує, то відкриється повідомлення про це.
     */
    @FXML
    protected void onAddEdgeButtonClick() {
        try {
            int value1 = Integer.parseInt(addEdgeSourceValueField.getText());
            int value2 = Integer.parseInt(addEdgeTargetValueField.getText());
            graphInstance.addEdge(value1, value2);
            addEdgeSourceValueField.clear();
            addEdgeTargetValueField.clear();
            updateGraphCanvas();
        } catch(NumberFormatException numberFormatException) {
            showErrorWindowForIncorrectValues();
        } catch (IllegalArgumentException illegalArgumentException) {
            showErrorWindow(illegalArgumentException.getMessage());
        }
    }

    /**
     * Обробник події натискання на кнопку "Remove vertex"
     * Коли користувач натискає на дану кнопку, зчитується значення вершини
     * з текстового поля вище, та якщо така вершина існує, то видаляє її з графу.
     * Якщо така вершина не існує, відкриється повідомлення про це.
     */
    @FXML
    protected void onRemoveVertexButtonClick() {
        try {
            int value = Integer.parseInt(removeVertexValueField.getText());
            graphInstance.removeVertex(value);
            removeVertexValueField.clear();
            updateGraphCanvas();
        } catch(NumberFormatException numberFormatException) {
            showErrorWindowForIncorrectValues();
        } catch (IllegalArgumentException illegalArgumentException) {
            showErrorWindow(illegalArgumentException.getMessage());
        }
    }

    /**
     * Обробник події натискання на кнопку "Remove edge"
     * Коли користувач натискає на дану кнопку, зчитується початок та кінець ребра
     * з текстового поля вище та якщо таке ребро існує, то видаляє його з графу.
     * Якщо таке ребро не існує, то відкриється повідомлення про це.
     */
    @FXML
    protected void onRemoveEdgeButtonClick() {
        try {
            int value1 = Integer.parseInt(removeEdgeSourceValueField.getText());
            int value2 = Integer.parseInt(removeEdgeTargetValueField.getText());
            graphInstance.removeEdge(value1, value2);
            removeEdgeSourceValueField.clear();
            removeEdgeTargetValueField.clear();
            updateGraphCanvas();
        } catch(NumberFormatException numberFormatException) {
            showErrorWindowForIncorrectValues();
        } catch (IllegalArgumentException illegalArgumentException) {
            showErrorWindow(illegalArgumentException.getMessage());
        }
    }

    /**
     * Обробник події натискання на кнопку "Clear graph"
     * Якщо користувач натисне на кнопку "Clear graph",
     * то відкриється діалогове вікно, яке запитає підтвердження,
     * чи дійсно користувач хоче видалити граф, якщо натиснути "Yes", то граф видалиться,
     * а якщо "No", то не видалиться
     */
    @FXML
    protected void onClearGraphButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation", ButtonType.YES, ButtonType.CANCEL);
        alert.setContentText("Are you sure?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                clearGraph();
            }
        });
    }

    /**
     * Метод, який очищає вміст графу та оновлює панель для візуалізації
     */
    protected void clearGraph() {
        graphInstance.clearGraph();
        updateGraphCanvas();
    }

    /**
     * Метод, що оновлює панель для візуалізації відповідно до вмісту графа
     */
    public void updateGraphCanvas() {
        clearCanvas();

        GraphInstance graphInstance = GraphInstance.getInstance();
        if (graphInstance.getGraph().vertexSet().size() > 1) {
            graphInstance.updateGraphPositions(RADIUS_FOR_CIRCLE);
        }

        for (DefaultEdge edge : graphInstance.getGraph().edgeSet()) {
            addEdge(graphInstance.getGraph().getEdgeSource(edge), graphInstance.getGraph().getEdgeTarget(edge));
        }

        for (Vertex vertex : graphInstance.getGraph().vertexSet()) {
            addNode(vertex);
        }
    }

    /**
     * Метод, який викликає повідомлення про некоректні дані введені в текстове поле
     */
    private void showErrorWindowForIncorrectValues() {
        showErrorWindow("Incorrect value in the field!\nFill proper integer value");
    }

    /**
     * Метод, що викликає повідомлення про помилку
     * @param message - повідомлення
     */
    private void showErrorWindow(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Метод, який очищує вміст графа
     */
    private void clearCanvas() {
        graphCanvas.getChildren().clear();
    }

    /**
     * Метод, що будує ребро у вікні
     * @param vertex1 - початок ребра
     * @param vertex2 - кінець ребра
     */
    private void addEdge(Vertex vertex1, Vertex vertex2) {
        Line line = new Line(vertex1.getX(), vertex1.getY(), vertex2.getX(), vertex2.getY());
        line.setStrokeWidth(LINE_STROKE_WIDTH);
        line.setStroke(COLOR_FOR_CIRCLE_STROKE);
        graphCanvas.getChildren().add(line);
    }

    /**
     * Метод, що будує вершину у вікні
     * @param vertex - вершина
     */
    private void addNode(Vertex vertex) {
        Circle circle = createCircle();

        StackPane stack = new StackPane();
        stack.setLayoutX(vertex.getX() - RADIUS_FOR_CIRCLE);
        stack.setLayoutY(vertex.getY() - RADIUS_FOR_CIRCLE);
        stack.getChildren().addAll(circle, createText(String.valueOf(vertex.getValue())));

        graphCanvas.getChildren().add(stack);
    }

    /**
     * @return графічний елемент кола
     */
    private Circle createCircle() {
        Circle circle = new Circle(RADIUS_FOR_CIRCLE, COLOR_FOR_CIRCLE_BACKGROUND);
        circle.setStrokeWidth(CIRCLE_STROKE_WIDTH);
        circle.setStroke(COLOR_FOR_CIRCLE_STROKE);

        return circle;
    }

    /**
     * @param value - текст
     * @return графічний елемент тексту
     */
    private Text createText(String value) {
        Text text = new Text(value);
        text.setFont(new Font(FONT, FONT_SIZE));
        text.setBoundsType(TextBoundsType.VISUAL);

        return text;
    }
}