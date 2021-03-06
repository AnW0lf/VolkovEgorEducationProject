package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;

public class Piece {
    protected Pair<Integer, Integer> coordinate;
    private String symbol;
    int move = 0;
    Color color;

    Piece(String symbol, Pair<Integer, Integer> coordinate, Color color) {
        this.coordinate = coordinate;
        this.symbol = symbol;
        this.color = color;
    }

    public void setCoordinate(Pair<Integer, Integer> coordinate) {
        this.coordinate = coordinate;
        move++;
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
