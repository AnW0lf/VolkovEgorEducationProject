package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;

public class King extends Piece {
    public static final String SYMBOL_WHITE = "♔";
    public static final String SYMBOL_BLACK = "♚";

    public King(Pair<Integer, Integer> coordinate, Color color) {
        super(color == Color.WHITE ? SYMBOL_WHITE : SYMBOL_BLACK, coordinate, color);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> movablePoints(Field field) {
        LinkedList<Pair<Integer, Integer>> points = new LinkedList<>();
        points.addAll(checkPoint(field, 1, 1));
        points.addAll(checkPoint(field, 1, -1));
        points.addAll(checkPoint(field, -1, 1));
        points.addAll(checkPoint(field, -1, -1));
        points.addAll(checkPoint(field, 1, 0));
        points.addAll(checkPoint(field, 0, 1));
        points.addAll(checkPoint(field, -1, 0));
        points.addAll(checkPoint(field, 0, -1));
        return points;
    }

    @Override
    public boolean isMovable(Field field) {
        return !movablePoints(field).isEmpty();
    }

    private LinkedList<Pair<Integer, Integer>> checkPoint(Field field, int vertical, int horizontal) {
        LinkedList<Pair<Integer, Integer>> points = new LinkedList<>();
        Pair<Integer, Integer> point = new Pair<>(coordinate.getKey() + vertical, coordinate.getValue() + horizontal);
        if (Field.inRange(point)) {
            if (field.getSymbol(point).isEmpty())
                points.add(point);
            else {
                if (!field.getColor(point, color).equals(color))
                    points.add(point);
            }
        }
        return points;
    }
}
