package com.example.graphproject;

import com.example.graphproject.controller.MainController;
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
        instance.addEdge(3, 4);
        instance.addEdge(4, 5);
        instance.addEdge(1, 5);
        instance.addEdge(3, 5);
        instance.addEdge(2, 5);
        //instance.printGraph();

//        MainController controller = new MainController();
//        controller.updateGraphCanvas();

        launch();
    }


}