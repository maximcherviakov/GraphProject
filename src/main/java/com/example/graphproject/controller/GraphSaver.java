package com.example.graphproject.controller;

import com.example.graphproject.model.GraphInstance;
import com.example.graphproject.model.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphSaver {
    private static final String FILE_PATH = "savedGraph/graph.json";

    private GraphSaver() {

    }

    public static void saveGraph() throws IOException {
        GraphInstance instance = GraphInstance.getInstance();
        Graph<Vertex, DefaultEdge> graph = instance.getGraph();

        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            Map<String, ArrayList<Map<String, String>>> graphMap = new HashMap<>();
            ArrayList<Map<String, String>> nodesJsonArray = new ArrayList<>();
            ArrayList<Map<String, String>> edgesJsonArray = new ArrayList<>();

            for (Vertex vertex : graph.vertexSet()) {
                Map<String, String> vertexMap = new HashMap<>();
                vertexMap.put("value", String.valueOf(vertex.getValue()));
                vertexMap.put("x", String.valueOf(vertex.getX()));
                vertexMap.put("y", String.valueOf(vertex.getY()));
                nodesJsonArray.add(vertexMap);
            }

            for (DefaultEdge edge : graph.edgeSet()) {
                Map<String, String> edgeMap = new HashMap<>();
                edgeMap.put("source", String.valueOf(graph.getEdgeSource(edge).getValue()));
                edgeMap.put("target", String.valueOf(graph.getEdgeTarget(edge).getValue()));
                edgesJsonArray.add(edgeMap);
            }

            graphMap.put("nodes", nodesJsonArray);
            graphMap.put("edges", edgesJsonArray);

            JSONObject graphJson = new JSONObject(graphMap);
            graphJson.writeJSONString(fileWriter);
        } catch (IOException e) {
            throw new IOException("Saving file failed!");
        }
    }

    public static void loadGraphFromFile() throws Exception {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            GraphInstance graphInstance = GraphInstance.getInstance();
            JSONParser parser = new JSONParser();
            JSONObject graph = (JSONObject) parser.parse(reader);
            JSONArray nodesArray = (JSONArray) graph.get("nodes");
            JSONArray edgesArray = (JSONArray) graph.get("edges");

            for (Object o : nodesArray) {
                JSONObject object = (JSONObject) o;
                graphInstance.addVertex(new Vertex(Integer.parseInt((String) object.get("value")),
                        Double.parseDouble((String) object.get("x")),
                        Double.parseDouble((String) object.get("y"))));
            }

            for (Object o : edgesArray) {
                JSONObject object = (JSONObject) o;
                graphInstance.addEdge(Integer.parseInt((String) object.get("source")),
                        Integer.parseInt((String) object.get("target")));

            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cannot find file with graph");
        } catch (Exception e) {
            throw new Exception("Something went wrong while getting graph from file!");
        }
    }
}
