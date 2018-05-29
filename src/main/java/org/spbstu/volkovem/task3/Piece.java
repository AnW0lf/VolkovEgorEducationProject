package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;

public class Piece {
    protected Pair<Integer, Integer> coordinate;
    protected String symbol;
    protected Color color;

    public Piece(String symbol, Pair<Integer, Integer> coordinate, Color color) {
        this.coordinate = coordinate;
        this.symbol = symbol;
        this.color = color;
    }

    public void setCoordinate(Pair<Integer, Integer> coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isMovable(Field field) {
        return false;
    }

    public LinkedList<Pair<Integer, Integer>> movablePoints(Field field) {
        return new LinkedList<>();
    }

    public enum Color {WHITE, BLACK}

    @Override
    public String toString() {
        return symbol;
    }

}
