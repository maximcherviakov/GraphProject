package com.example.graphproject;

import com.example.graphproject.model.GraphInstance;
import com.example.graphproject.model.Vertex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Graph");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        GraphInstance instance = GraphInstance.getInstance();

        instance.addVertex(1);
        instance.addVertex(2);
        instance.addVertex(3);
        instance.addVertex(4);
        instance.addVertex(5);

        instance.addEdge(1, 2);
        instance.addEdge(2, 3);
        instance.addEdge(3, 1);
        instance.addEdge(4, 5);
        instance.addEdge(1, 5);
        instance.addEdge(3, 5);
        instance.addEdge(2, 5);
        instance.printGraph();

        for (Vertex vertex : instance.getGraph().vertexSet()) {
            System.out.println(vertex);
        }

        launch();
    }


}