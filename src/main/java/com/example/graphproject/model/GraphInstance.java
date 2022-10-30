package com.example.graphproject.model;

import org.jgrapht.Graph;
import org.jgrapht.alg.drawing.FRLayoutAlgorithm2D;
import org.jgrapht.alg.drawing.model.Box2D;
import org.jgrapht.alg.drawing.model.LayoutModel2D;
import org.jgrapht.alg.drawing.model.MapLayoutModel2D;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Клас GraphInstance - клас, що використовує патерн singleton.
 * Потрібен для зберігання графа та виконання дій над ним
 */
public class GraphInstance {
    private static final int CRITICAL_NUMBER_OF_VERTICES = 10;
    private static final double INITIAL_WIDTH_OF_PLANE = 500;
    private static final double INITIAL_HEIGHT_OF_PLANE = 500;
    private static double WIDTH_OF_PLANE = 500;
    private static double HEIGHT_OF_PLANE = 500;

    private static SimpleGraph<Vertex, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
    private static GraphInstance instance;

    private GraphInstance() {
        
    }

    public static GraphInstance getInstance() {
        if (instance == null) {
            instance = new GraphInstance();
        }
        return instance;
    }

    public Graph<Vertex, DefaultEdge> getGraph() {
        return graph;
    }

    public void addVertex(Integer value) throws IllegalArgumentException {
        WIDTH_OF_PLANE = INITIAL_WIDTH_OF_PLANE + 0.5 * INITIAL_WIDTH_OF_PLANE * (graph.vertexSet().size() / 10);
        HEIGHT_OF_PLANE = INITIAL_HEIGHT_OF_PLANE + 0.5 * INITIAL_HEIGHT_OF_PLANE * (graph.vertexSet().size() / 10);

        Vertex vertex = new Vertex(value, Math.random() * WIDTH_OF_PLANE, Math.random() * HEIGHT_OF_PLANE);
        if (!graph.containsVertex(vertex)) {
            graph.addVertex(vertex);
        } else {
            throw new IllegalArgumentException("Vertex with this value is already exist!");
        }
    }

    public void addEdge(Integer value1, Integer value2) throws IllegalArgumentException {
        ArrayList<Object> vertices = new ArrayList<>(Arrays.asList(graph.vertexSet().toArray()));
        Vertex vertex1;
        Vertex vertex2;

        try {
            vertex1 = (Vertex) vertices.get(vertices.indexOf(new Vertex(value1)));
            vertex2 = (Vertex) vertices.get(vertices.indexOf(new Vertex(value2)));
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new IllegalArgumentException("Vertex with this value does not exist!");
        }

        if (!graph.containsEdge(vertex1, vertex2)) {
            graph.addEdge(vertex1, vertex2);
        } else {
            throw new IllegalArgumentException("Here is a duplicate of an edge!");
        }
    }

    public void removeVertex(Integer value) throws IllegalArgumentException {
        Vertex vertex = new Vertex(value);
        if (!graph.removeVertex(vertex)) {
            throw new IllegalArgumentException("Vertex with this value does not exist!");
        }
    }

    public void removeEdge(Integer value1, Integer value2) {
        Vertex vertex1 = new Vertex(value1);
        Vertex vertex2 = new Vertex(value2);

        if (graph.containsEdge(vertex1, vertex2)) {
            graph.removeEdge(vertex1, vertex2);
        } else {
            throw new IllegalArgumentException("This edge does not exist!");
        }
    }

    public void clearGraph() {
        graph = new SimpleGraph<>(DefaultEdge.class);
    }

    /**
     * Даний метод обраховує координати вершин для силовим алгоритмом побудови графа.
     * Він потрібен, щоб граф приймав гарний та зрозумілий вигляд
     * @param radius - радіус кола, що позначає вершину
     */
    public void updateGraphPositions(double radius) {
        FRLayoutAlgorithm2D<Vertex, DefaultEdge> algorithm2D = new FRLayoutAlgorithm2D<>();
        Box2D box2D = new Box2D(WIDTH_OF_PLANE, HEIGHT_OF_PLANE);
        LayoutModel2D<Vertex> layoutModel2D = new MapLayoutModel2D<>(box2D);
        algorithm2D.layout(graph, layoutModel2D);

        for (Vertex vertex : graph.vertexSet()) {
            vertex.setX(layoutModel2D.get(vertex).getX() + radius);
            vertex.setY(layoutModel2D.get(vertex).getY() + radius);
        }
    }

    public void printGraph() {
        System.out.println("Vertices:");

        for (Vertex vertex : graph.vertexSet()) {
            System.out.println(vertex.toString());
        }

        System.out.println("Edges:");

        for (DefaultEdge edge : graph.edgeSet()) {
            System.out.println(edge.toString());
        }
    }
}