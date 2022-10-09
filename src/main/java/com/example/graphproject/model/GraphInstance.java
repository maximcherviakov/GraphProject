package com.example.graphproject.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphInstance {
    private static GraphInstance instance = null;
    private Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    private GraphInstance() {

    }

    public GraphInstance getInstance() {
        if (instance == null) {
            instance = new GraphInstance();
        }
        return instance;
    }

    public void addVertex(Integer integer) throws Exception {
        if (!graph.containsVertex(integer)) {
            graph.addVertex(integer);
        } else {
            throw new Exception("Here is a duplicate of a vertex!");
        }
    }

    public void addEdge(Integer integer1, Integer integer2) throws Exception {
        if (!graph.containsEdge(integer1, integer2)) {
            graph.addEdge(integer1, integer2);
        } else {

            throw new Exception("Here is a duplicate of an edge!");
        }
    }

    public void printGraph() {
        System.out.println("Vertices:");

        for (Integer i : graph.vertexSet()) {
            System.out.println(i);
        }

        System.out.println("Edges:");

        for (Integer i : graph.vertexSet()) {
            System.out.println(i);
        }
    }
}