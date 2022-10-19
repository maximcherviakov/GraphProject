package com.example.graphproject.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.nio.GraphImporter;

import java.util.HashMap;
import java.util.Objects;

public class GraphInstance {
    private static SimpleGraph<Integer, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
    private static HashMap<Integer, Point> mapOfPoints = new HashMap<>();
    private static GraphInstance instance;

    private GraphInstance() {

    }

    public static GraphInstance getInstance() {
        if (instance == null) {
            instance = new GraphInstance();
        }
        return instance;
    }

    public Graph<Integer, DefaultEdge> getGraph() {
        return graph;
    }

    public Point getCoordinatesByVertexValue(Integer value) {
        return mapOfPoints.get(value);
    }

    public void addVertex(Integer value) throws Exception {
        if (!graph.containsVertex(value)) {
            graph.addVertex(value);
            mapOfPoints.put(value, new Point(Math.random(), Math.random()));
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

    private class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private class Vertex {
        private int value;
        private double x;
        private double y;

        public Vertex(int value, double x, double y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return value == vertex.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "value=" + value +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}