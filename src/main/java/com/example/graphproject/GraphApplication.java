package com.example.graphproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        stage.setTitle("Graph");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}