package com.example.graphproject.model;

import java.util.Objects;

public class Vertex {
    private int value;
    private double x;
    private double y;

    public Vertex(int value) {
        this.value = value;
    }

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
