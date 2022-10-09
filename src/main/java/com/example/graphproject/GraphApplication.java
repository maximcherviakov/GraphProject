package com.example.graphproject;

import com.example.graphproject.model.GraphInstance;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.dot.DOTExporter;

import java.io.IOException;
import java.net.URI;

public class GraphApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Graph");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();

        Graph<Integer, DefaultEdge> graph = GraphInstance.getInstance();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(3,4);

        System.out.println(graph.toString());

        graph.removeAllEdges(0,1);


        System.out.println(graph.toString());
    }
}