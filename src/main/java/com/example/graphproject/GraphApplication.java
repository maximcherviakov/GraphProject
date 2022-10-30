package com.example.graphproject;

import com.example.graphproject.model.GraphInstance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Клас GraphApplication, що наслідується від Application,
 * який містить класи бібліотеки JavaFX.
 */
public class GraphApplication extends Application {
    /**
     * Перевизначений метод з класу Application.
     * Цей метод виконується першим та потрібен для налаштувань вікна
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Завантаження файлу main-view.fxml, який містить у собі розмітку вікна.
        // Також даний файл використовує файл styles.css який містить стилі об'єктів вікна.
        FXMLLoader fxmlLoader = new FXMLLoader(GraphApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(String.valueOf(GraphApplication.class.getResource("graph.png"))));
        stage.setTitle("Graph");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        GraphInstance instance = GraphInstance.getInstance();
        for (int i = 0; i < 10; i++) {
            instance.addVertex(i);
        }

        launch();
    }
}